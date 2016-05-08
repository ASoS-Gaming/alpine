/**
 * Build parameters, must be adjusted when forked!
 **/
env.DockerImageName = 'blacklabelops/alpine'
def dockerTags = ["latest","3.3"] as String[]
dockerTestCommands =
  ["echo hello world",
   "ps -All",
   "uname -r",
   "whoami",
   "cat /etc/hosts",
   "cat /etc/passwd"] as String[]

def branchName = getBranchName()

stage 'Build Image'
echo 'Building the image'
for (int i=0;i < dockerTags.length;i++) {
  buildImage(env.DockerImageName,dockerTags[i],branchName)
}

stage 'Test Image'
echo 'Testing the image'
for (int i=0;i < dockerTags.length;i++) {
  testImage(env.DockerImageName,dockerTags[i],branchName)
}

def testImage(imageName, tagName, branchName) {
  def branchSuffix = branchName?.trim() ? '-' + branchName : ''
  def image = imageName + ':' + tagName + branchSuffix
  for (int i=0;i < dockerTestCommands.length;i++) {
    sh 'docker run --rm ' + image + ' ' + dockerTestCommands[i]
  }
}

def buildImage(imageName, tagName, branchName) {
  def branchSuffix = branchName?.trim() ? '-' + branchName : ''
  def image = imageName + ':' + tagName + branchSuffix
  echo 'Building: ' + image
  sh 'docker build --no-cache -t ' + image + ' .'
}

def getBranchName() {
  def branchName = env.JOB_NAME.replaceFirst('.+/', '')
  echo 'Building on Branch: ' + branchName
  def tagPostfix = ''
  if (branchName != null && !'master'.equals(branchName)) {
    tagPostfix = branchName
  }
  return tagPostfix
}
