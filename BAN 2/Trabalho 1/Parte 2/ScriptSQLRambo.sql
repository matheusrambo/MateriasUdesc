CREATE TABLE Hospede
(
    id_hospede integer NOT NULL,
    cpf character varying(11) NOT NULL,
    nome character varying(100) NOT NULL,
    telefone character varying(12) NOT NULL,
    idade integer NOT NULL,
    endereco character varying(50) NOT NULL,
    CONSTRAINT Hospede_pkey PRIMARY KEY (id_hospede)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Hospede OWNER TO postgres;



CREATE TABLE Frigobar
(
    id_frigobar integer NOT NULL,
    descricao character varying(100) NOT NULL,
    CONSTRAINT Frigobar_pkey PRIMARY KEY (id_frigobar)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Frigobar OWNER TO postgres;




CREATE TABLE Itens
(
    id_item integer NOT NULL,
    nome character varying(50) NOT NULL,
    CONSTRAINT Itens_pkey PRIMARY KEY (id_item)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Itens OWNER TO postgres;




CREATE TABLE Tipo_quarto
(
    id_tipo_quarto integer NOT NULL,
    descricao character varying(100) NOT NULL,
    CONSTRAINT Tipo_quarto_pkey PRIMARY KEY (id_tipo_quarto)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Tipo_quarto OWNER TO postgres;




CREATE TABLE Acompanhantes
(
    id_acompanhante integer NOT NULL,
    cpf integer(11) NOT NULL,
    nome character varying(100) NOT NULL,
    idade integer NOT NULL,
    id_hospede integer,
    CONSTRAINT Acompanhantes_pkey PRIMARY KEY (id_acompanhante),
    CONSTRAINT id_hospede FOREIGN KEY (id_hospede)
        REFERENCES Hospede (id_hospede) MATCH SIMPLE
        ON UPDATE CASCADE ON DELETE SET NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Acompanhantes OWNER TO postgres;




CREATE TABLE Itens_frigobar
(
    id_itens_frigobar integer NOT NULL,
    id_frigobar integer NOT NULL,
    id_item integer NOT NULL,
    quantidade integer NOT NULL,
    CONSTRAINT Itens_frigobar_pkey PRIMARY KEY (id_itens_frigobar),
    CONSTRAINT id_frigobar FOREIGN KEY (id_frigobar)
        REFERENCES Frigobar (id_frigobar) MATCH SIMPLE
        ON UPDATE CASCADE ON DELETE SET NULL,
    CONSTRAINT id_item FOREIGN KEY (id_item)
        REFERENCES Itens (id_item) MATCH SIMPLE
        ON UPDATE CASCADE ON DELETE SET NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Itens_frigobar OWNER TO postgres;




CREATE TABLE Quarto
(
    id_quarto integer NOT NULL,
    quant_camas integer NOT NULL,
    preco character varying(10) NOT NULL,
    descricao character varying(100) NOT NULL,
    comodidade character varying(100) NOT NULL,
    id_frigobar integer,
    id_tipo_quarto integer,
    CONSTRAINT Quarto_pkey PRIMARY KEY (id_quarto),
    CONSTRAINT id_frigobar FOREIGN KEY (id_frigobar)
        REFERENCES Frigobar (id_frigobar) MATCH SIMPLE
        ON UPDATE CASCADE ON DELETE SET NULL,
    CONSTRAINT id_tipo_quarto FOREIGN KEY (id_tipo_quarto)
        REFERENCES Tipo_quarto (id_tipo_quarto) MATCH SIMPLE
        ON UPDATE CASCADE ON DELETE SET NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Quarto OWNER TO postgres;




CREATE TABLE Reserva
(
    id_reserva integer NOT NULL,
    pago integer NOT NULL,
    desconto character varying(10) NOT NULL,
    data_entrada date NOT NULL,
    data_saida date NOT NULL,
    id_hospede integer NOT NULL,
    CONSTRAINT Reserva_pkey PRIMARY KEY (id_reserva),
    CONSTRAINT id_hospede FOREIGN KEY (id_hospede)
        REFERENCES Hospede (id_hospede) MATCH SIMPLE
        ON UPDATE CASCADE ON DELETE SET NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Reserva OWNER TO postgres;




CREATE TABLE Quartos
(
    id_quartos integer NOT NULL,
    id_quarto integer NOT NULL,
    id_reserva integer NOT NULL,
    CONSTRAINT Quartos_pkey PRIMARY KEY (id_quartos),
    CONSTRAINT id_quarto FOREIGN KEY (id_quarto)
        REFERENCES Quarto (id_quarto) MATCH SIMPLE
        ON UPDATE CASCADE ON DELETE SET NULL,
    CONSTRAINT id_reserva FOREIGN KEY (id_reserva)
        REFERENCES Reserva (id_reserva) MATCH SIMPLE
        ON UPDATE CASCADE ON DELETE SET NULL
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Quartos OWNER TO postgres;