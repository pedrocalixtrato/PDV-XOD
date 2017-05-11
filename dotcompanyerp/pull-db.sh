ssh -t root@198.50.171.114 "sudo -u postgres pg_dump -Fc DOTERP > /tmp/doterp.pgbak"
scp root@198.50.171.114:/tmp/doterp.pgbak /tmp
