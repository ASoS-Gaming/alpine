# Docker Base Image for ASoS Images

Alpine images are smaller than full distributions, e.g. CentOS. This is a base image with some tweaks based on the official alpine image on Dockerhub.

* Includes updates

# Build the Image

~~~~
$ docker build -t asos/alpine .
~~~~

# Run the Image

~~~~
$ docker run -it --rm asos/alpine bash
~~~~
