# send start procedure e-mail to users

# upgrade the DB

```sql
ALTER TABLE ieiparam ADD COLUMN formula character varying(10000);

ALTER TABLE acevent ADD COLUMN style character varying(255);

ALTER TABLE bookmark ADD COLUMN photo character varying(255);

CREATE TABLE apikey
(
  id bigint NOT NULL,
  details character varying(255),
  expiration timestamp without time zone,
  token character varying(255),
  profile character varying(255),
  username character varying(255),
  user_id bigint,
  CONSTRAINT apikey_pkey PRIMARY KEY (id),
  CONSTRAINT fk75462a0547140efe FOREIGN KEY (user_id)
      REFERENCES t_user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT apikey_token_key UNIQUE (token)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE apikey
  OWNER TO <myuser>;

ALTER TABLE fwottype ADD COLUMN photo character varying(255);
```


# disable pingdom alert
https://my.pingdom.com/newchecks/checks


# nginx

enable WebSocket

```
location /fleet {
		proxy_pass http://localhost:9004;
		proxy_set_header X-Real-IP $remote_addr;
		proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
		proxy_set_header Host $http_host;
		proxy_set_header X-NginX-Proxy true;

		proxy_redirect off;

		proxy_http_version 1.1;
		proxy_set_header Upgrade $http_upgrade;
		proxy_set_header Connection "upgrade";
}
```

```bash
sudo /etc/init.d/nginx restart
```

# upgrade fleet-monitor
```bash
cd ~/fw/demo/fleet-monitor
git pull origin release-monkey-1
```

```bash
/etc/init.d/fw-fleet-demo restart
```

# upgrade front-end



# upgrade deidentified site (demo)
```bash
cd fw/demo/wilco-desktop-client
git pull origin release-monkey-1
npm install
bower install
```

**check babysmash is OK**

```bash
grunt build
```

# create an api-key for pingdom and update pingdom

# send end procedure e-mail to users
