aws-deploy
==========

Use a Gradle DSL to create AWS subnets, security groups, and instances.

In the spirit of "infrastructure as code", this project allows one to create clones of the production environment
in AWS, perhaps to be used as
staging, test, etc.  The construction of the subnets and security groups is scripted with Gradle starting from a
config file.  Chef is used (using knife ec2) to create the actual instances, apply the proper recipes, etc.

Currently (July 2014) this is very much under construction (only a few days old) and there's a lot of hard-coded stuff
that still needs to be fixed.

## Dependencies
This project, as it stands now, requires
* aws cli
* knife
* knife ec2
* gradle

## Tasks
* createAllSubnets - it will create a subnet in each of the availability zones listed in the config file.  The subnet CIDRs are specified only as a single number; since I'm using /24 subnets the third octect is all you need to specify (the VPC is /16).
* createAllSecurityGroups - you can specify security groups by name, and ingress rules either by CIDR or subnet name.  Since the subnets are created one per availability zone, the security group will contain each of those subnets in its rules.
* createAllInstances - provisions the instances in the requested subnet and with the requested security groups, as well as bootstraps them for Chef and applies a runlist.  You'll need to have created and configured your local chef-repo so that you can talk to some Chef Server somewhere (hosted Enterprise Chef is free up to 5 nodes).

## Design
The config file is read in with the ConfigSlurper, and fed into a Topology object to be parsed and have the actual IPs set, etc.  The Topology object is held as a Gradle Project ext property, and fed into the tasks that need it.  The Gradle tasks are custom tasks that form an inheritance hierarchy.  Currently, all the Gradle code is in the build.gradle file.

## TTD
* make room for instances that are not replicated across availability zones, like bastion servers, CI servers, etc.
* get RDS automation working
* automatically add the RDS credentials to the Chef databag
* probably some PKI key management, to enable login.  I have to consider the security implications, however.
