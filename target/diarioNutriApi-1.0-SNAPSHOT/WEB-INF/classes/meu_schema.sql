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