availabilityZones = ['us-west-2a', 'us-west-2b', 'us-west-2c']
vpc.baseIp = "10.0."  // assumes /16

securityGroups = [
	[name: 'AppServer', description: 'Tomcat access: 443 from ELB, 8080 from Bastion', ingressRules : [ 
			[port: 443, cidr: '0.0.0.0/0'], 
			[port: 8080, subnet: 'Public']
		]
	],
	[name: 'PublicLb', description: '443 from the world', ingressRules : [ 
			[port: 443, cidr: '0.0.0.0/0']
		]
	]
]

instances = [
	[ami: 'ami-b5a9d485', key: 'GradleKeyPair', securityGroup: 'AppServer', type: "m3.medium", subnet: 'App']
]

environments {
	prod {
		// Subnets assum vpc.baseIp + cidr + ".0/24"
		subnets = [
			[name: 'Public', cidrs: [1,2,3]],
			[name: 'App', cidrs: [4,5,6]],
			[name: 'Private', cidrs: [7,8,9]]
		]
	}
	dev {
		subnets = [
			[name: 'Public', cidrs: [10,11,12]],
			[name: 'App', cidrs: [13,14,15]]/*,
			[name: 'Private', cidrs: [16,17,18]]*/
		]
	}
}