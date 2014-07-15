aws-deploy
==========

Use a Gradle DSL to create AWS subnets, security groups, and instances.

In the spirit of "infrastructure as code", this project allows one to create clones of the production environment
in AWS, perhaps to be used as
staging, test, etc.  The construction of the subnets and security groups is scripted with Gradle starting from a
config file.  Chef is used (using knife ec2) to create the actual instances, apply the proper recipes, etc.

Currently (July 2014) this is very much under construction (only a few days old) and there's a lot of hard-coded stuff
that still needs to be fixed.
