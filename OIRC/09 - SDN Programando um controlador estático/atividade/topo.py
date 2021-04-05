#!/usr/bin/python
# -*- coding: utf-8 -*-
from mininet.net import Mininet
from mininet.link import TCLink
from mininet.topo import Topo
from mininet.node import CPULimitedHost
from mininet.util import dumpNodeConnections
from mininet.cli import CLI
from mininet.node import OVSKernelSwitch, RemoteController
from mininet.log import setLogLevel
import time

c0 = RemoteController('c0', ip='127.0.0.1')

class Topo2402(Topo):
    def __init__(self,h=4,s=3,**opts):
        hostttt= ['A', 'B', 'C', 'D']
        Topo.__init__(self, **opts)
        switchs = [self.addSwitch('s%d' % j) for j in range(1, s+1)]
        hosts = [self.addHost(hostttt[i], cpu=.5/h) for i in range(0, h)]
        print(hosts,switchs)

        # Switchs
        self.addLink(switchs[0], switchs[2], port1=3, port2=2, cls=TCLink, bw=1000, delay='5ms')
        self.addLink(switchs[0], switchs[1], port1=1, port2=2, cls=TCLink, bw=1000, delay='5ms')
        self.addLink(switchs[1], switchs[2], port1=3, port2=1, cls=TCLink, bw=1000, delay='5ms')
        
        #hosts to switch
        self.addLink(hosts[0], switchs[0], port1=0, port2=2, cls=TCLink, bw=1000, delay='5ms')
        self.addLink(hosts[1], switchs[1], port1=0, port2=1, cls=TCLink, bw=1000, delay='5ms')
        self.addLink(hosts[2], switchs[2], port1=0, port2=3, cls=TCLink, bw=1000, delay='5ms')
        self.addLink(hosts[3], switchs[2], port1=0, port2=4, cls=TCLink, bw=1000, delay='5ms')

def perfTest():

    topo = Topo2402(h=4,s=3)
    net = Mininet( topo=topo, controller=c0, link=TCLink, host=CPULimitedHost, switch=OVSKernelSwitch)
    net.start()

    print("Dumping host connections")
    dumpNodeConnections(net.hosts)
    CLI(net)
    net.stop()

    

if __name__ == '__main__':
    setLogLevel('info')
    perfTest()


