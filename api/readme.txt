1.
Using increment by 1 for the SEQUENCE generator. The default is 50 which seems a bit high for me. I probably would prefer 10. This will be considered as enhancement. I did most of it.
I just have to change the value from 1 to 10 if required.

2.
API versioning is implemented. I am starting with 1.1

3.
Passwords can be stored as secret. I couldn't do it as I was initially having some problem with my local docker setup.

4.
The spring boot docker image is a "layered" image. It is very beneficial to create docker images in such a way.

5.
I am using PostgreSQL text DataType and then adding CONSTRAINT afterward. It has a lot of benefits this way.

6.
Docker images are with default "latest" tags. If needed, we may push images with different tags for "docker image" version control. I am considering this as an enhancement to do in the future.