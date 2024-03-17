create table tab_usuario(
    cd_usuario integer primary key auto_increment,
    tx_usuario varchar(30),
    tx_email varchar(50),
    tx_senha varchar(999),
    tx_foto varchar(999),
    ck_sexo integer,
    dt_nascimento date,
    vl_altura numeric(11,2),
    vl_peso numeric(11,2)
);

create table tab_alimento(
    cd_alimento integer primary key auto_increment,
    tx_alimento varchar(30),
    vl_kcal integer,
    vl_proteina numeric(11,2),
    vl_carboidrato numeric(11,2),
    vl_gordura numeric(11,2)
);

create table tab_refeicao(
    cd_refeicao integer primary key auto_increment,
    tx_refeicao varchar(30),
    cd_usuario integer references tab_usuario (cd_usuario),
    dt_refeicao integer,
    cd_refeicao_tipo integer references tab_refeicao_tipo (cd_refeicao_tipo)
);

create table tab_refeicao_alimento(
    cd_refeicao_alimento integer primary key auto_increment,
    cd_alimento integer references tab_alimento (cd_alimento),
    cd_refeicao integer references tab_refeicao (cd_refeicao),
    cd_usuario integer references tab_usuario (cd_usuario)
);

create table tab_refeicao_tipo(
    cd_refeicao_tipo integer primary key auto_increment,
    tx_refeicao_tipo varchar(30)
);

alter table tab_usuario
add column vl_imc_atual numeric(11,2),
add column vl_imc_ideal numeric(11,2),
add column vl_peso_ideal numeric(11,2);

ALTER TABLE tab_refeicao
MODIFY COLUMN dt_refeicao date;

ALTER TABLE tab_usuario
MODIFY COLUMN vl_imc_ideal varchar(30),
MODIFY COLUMN vl_peso_ideal varchar(30);

ALTER TABLE tab_refeicao_tipo
ADD COLUMN cd_usuario integer references tab_usuario (cd_usuario);

ALTER TABLE tab_refeicao
ADD COLUMN tx_icon varchar(30);

ALTER TABLE tab_refeicao_tipo
ADD COLUMN tx_icon varchar(30);

ALTER TABLE tab_refeicao
    ADD COLUMN dt_hora_refeicao time(0);

ALTER TABLE tab_refeicao_tipo
    ADD COLUMN dt_hora_refeicao_tipo time(0);