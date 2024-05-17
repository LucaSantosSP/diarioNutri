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

DELIMITER //
CREATE TRIGGER set_valor_padrao_dt_hora_refeicao_tipo
    BEFORE INSERT ON tab_refeicao_tipo
    FOR EACH ROW
BEGIN
    IF NEW.tx_refeicao_tipo = 'Café da Manhã' THEN
        SET NEW.dt_hora_refeicao_tipo = '08:00:00';
    ELSEIF NEW.tx_refeicao_tipo = 'Almoço' THEN
        SET NEW.dt_hora_refeicao_tipo = '12:00:00';
    ELSEIF NEW.tx_refeicao_tipo = 'Janta' THEN
        SET NEW.dt_hora_refeicao_tipo = '18:00:00';
END IF;
END //
DELIMITER ;

UPDATE tab_refeicao_tipo
SET dt_hora_refeicao_tipo = CASE
    WHEN tx_refeicao_tipo = 'Café da Manhã' THEN '08:00:00'
    WHEN tx_refeicao_tipo = 'Almoço' THEN '12:00:00'
    WHEN tx_refeicao_tipo = 'Janta' THEN '18:00:00'
END;

DELIMITER //
CREATE TRIGGER incrementar_cd_refeicao_tipo
    BEFORE INSERT ON tab_refeicao_tipo
    FOR EACH ROW
BEGIN
    DECLARE proximo_cd_refeicao_tipo INT;
    SET proximo_cd_refeicao_tipo = COALESCE((SELECT MAX(cd_refeicao_tipo) FROM tab_refeicao_tipo), 0) + 1;
    SET NEW.cd_refeicao_tipo = proximo_cd_refeicao_tipo;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER set_valor_padrao_dt_hora_refeicao
    BEFORE INSERT ON tab_refeicao
    FOR EACH ROW
BEGIN
    IF NEW.tx_refeicao = 'Café da Manhã' THEN
        SET NEW.dt_hora_refeicao = '08:00:00';
    ELSEIF NEW.tx_refeicao = 'Almoço' THEN
        SET NEW.dt_hora_refeicao = '12:00:00';
    ELSEIF NEW.tx_refeicao = 'Janta' THEN
        SET NEW.dt_hora_refeicao = '18:00:00';
END IF;
END //
DELIMITER ;

UPDATE tab_refeicao
SET dt_hora_refeicao = CASE
   WHEN tx_refeicao = 'Café da Manhã' THEN '08:00:00'
   WHEN tx_refeicao = 'Almoço' THEN '12:00:00'
   WHEN tx_refeicao = 'Janta' THEN '18:00:00'
END;

DELIMITER //
CREATE TRIGGER incrementar_cd_refeicao
    BEFORE INSERT ON tab_refeicao
    FOR EACH ROW
BEGIN
    DECLARE proximo_cd_refeicao INT;
    SET proximo_cd_refeicao = COALESCE((SELECT MAX(cd_refeicao) FROM tab_refeicao), 0) + 1;
    SET NEW.cd_refeicao = proximo_cd_refeicao;
END //
DELIMITER ;

ALTER TABLE tab_alimento
ADD COLUMN vl_ml_agua varchar(10);

ALTER TABLE tab_alimento
    ADD COLUMN cd_usuario integer references tab_usuario (cd_usuario);

ALTER TABLE tab_alimento
    modify column vl_proteina varchar(20),
    modify column vl_carboidrato varchar(20),
    modify column vl_gordura varchar(20);

ALTER TABLE tab_alimento
CHANGE tx_alimento tx_alimento varchar(100),
CHANGE vl_ml_agua vl_umidade varchar(10);

ALTER TABLE tab_alimento
ADD COLUMN tx_grupo varchar(100),
ADD COLUMN vl_kj varchar(10),
ADD COLUMN vl_lipideos varchar(10),
ADD COLUMN vl_colesterol varchar(10),
ADD COLUMN vl_fibra_alimentar varchar(10),
ADD COLUMN vl_cinzas varchar(10),
ADD COLUMN vl_calcio varchar(10),
ADD COLUMN vl_magnesio varchar(10),
ADD COLUMN vl_manganes varchar(10),
ADD COLUMN vl_fosforo varchar(10),
ADD COLUMN vl_ferro varchar(10),
ADD COLUMN vl_sodio varchar(10),
ADD COLUMN vl_potassio varchar(10),
ADD COLUMN vl_cobre varchar(10),
ADD COLUMN vl_zinco varchar(10),
ADD COLUMN vl_retinol varchar(10),
ADD COLUMN vl_re varchar(10),
ADD COLUMN vl_rae varchar(10),
ADD COLUMN vl_tiamina varchar(10),
ADD COLUMN vl_riboflavina varchar(10),
ADD COLUMN vl_piridoxina varchar(10),
ADD COLUMN vl_niacina varchar(10),
ADD COLUMN vl_vitamina_c varchar(10);

create table tab_receita(
    cd_receita integer primary key auto_increment,
    cd_usuario integer references tab_usuario (cd_usuario),
    tx_titulo varchar(30),
    tx_tipo varchar(30),
    tx_foto varchar(999),
    vl_kcal integer,
    tx_ingredientes varchar(999),
    tx_preparo varchar(999)
);

ALTER TABLE tab_receita
MODIFY COLUMN tx_ingredientes TEXT,
MODIFY COLUMN tx_preparo TEXT;
