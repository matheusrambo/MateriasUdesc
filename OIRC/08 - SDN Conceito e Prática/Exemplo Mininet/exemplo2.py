#!/usr/bin/python
# -*- coding: utf-8 -*-
from mininet.net import Mininet
from mininet.link import TCLink
from mininet.topo import Topo
from mininet.node import CPULimitedHost
from mininet.cli import CLI
from mininet.node import OVSKernelSwitch, RemoteController
from mininet.log import setLogLevel

c0 = RemoteController('c0', ip='127.0.0.1')

class SimpleTop(Topo):

    '''Simple Topology with two switches and two hosts'''

    def __init__(self,h=2,s=2,**opts):

        Topo.__init__(self, **opts)
        switchs = [self.addSwitch('s%d' % j) for j in range(0, s)]
        hosts = [self.addHost('h%d' % i, cpu=.5/h) for i in range(0, h)]
        self.addLink(switchs[0], switchs[1], cls=TCLink, bw=1000,
                     delay='5ms')  # S1 to S2
        for i in range(1, h+1):
            for j in range(1, s+1):
                if i % j == 0:
                    self.addLink(hosts[i-1], switchs[j-1], cls=TCLink, bw=100, delay='10ms')  # H1 to S1]


def perfTest():
    topo = SimpleTop(h=4,s=2)
    net = Mininet( topo=topo, controller=c0, link=TCLink, host=CPULimitedHost, switch=OVSKernelSwitch)
    net.start()
    CLI(net)
    net.stop()

if __name__ == '__main__':
    setLogLevel('info')
    perfTest()


