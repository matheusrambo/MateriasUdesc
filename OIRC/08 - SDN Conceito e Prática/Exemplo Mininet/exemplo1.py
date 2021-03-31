#!/usr/bin/python
# -*- coding: utf-8 -*-
from mininet.net import Mininet
from mininet.link import TCLink
from mininet.topo import Topo
from mininet.node import CPULimitedHost
from mininet.cli import CLI


class SimpleTop(Topo):

    '''Simple Topology with two switches and two hosts'''

    def __init__(self,h=2,s=2,**opts):

        Topo.__init__(self, **opts)
        switchs = [self.addSwitch('s%d' % j) for j in range(0, s)]
        hosts = [self.addHost('h%d' % i, cpu=.5) for i in range(0, h)]
        self.addLink(switchs[0], switchs[1], cls=TCLink, bw=1000,
                     delay='5ms')  # S1 to S2
        for i in range(0, h):
            for j in range(0, s):
                if h % s == 0:
                    self.addLink(hosts[i], switchs[j], cls=TCLink, bw=100, delay='10ms')  # H1 to S1



topo = SimpleTop(h=4,s=2)
net = Mininet( topo=topo, link=TCLink, host=CPULimitedHost)
net.start()  
CLI(net)
net.stop()
