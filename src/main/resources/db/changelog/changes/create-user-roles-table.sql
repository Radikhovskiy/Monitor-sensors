CREATE TABLE IF NOT EXISTS public.user_roles
(
    user_id bigint NOT NULL,
    role character varying(255),
    CONSTRAINT user_roles_fk FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

--rollback DROP TABLE user-roles;
