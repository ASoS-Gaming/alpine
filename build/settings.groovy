/**
 * Jenkins 2.0 Buildfile
 **/

/**
 * Build parameters, must be adjusted when forked!
 *
 **/
dockerImageName = 'blacklabelops/alpine'
dockerTags = ["latest","3.3"] as String[]
dockerTestCommands =
 ["echo hello world",
  "ps -All",
  "uname -r",
  "whoami",
  "cat /etc/hosts",
  "cat /etc/passwd"] as String[]
dockerRepositories = [["docker.io","Dockerhub","DockerhubEmail"]] as String[][]

return this;