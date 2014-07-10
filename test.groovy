c = new ConfigSlurper('b').parse(new File("dev.groovy").toURL())
println c.foo