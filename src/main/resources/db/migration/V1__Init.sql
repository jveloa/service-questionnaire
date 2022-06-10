
--
-- Name: answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.answer (
                               id integer NOT NULL,
                               answer character varying NOT NULL
);


ALTER TABLE public.answer OWNER TO postgres;

--
-- Name: answer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.answer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.answer_id_seq OWNER TO postgres;

--
-- Name: answer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.answer_id_seq OWNED BY public.answer.id;


--
-- Name: group_question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.group_question (
                                       id integer NOT NULL,
                                       description character varying,
                                       name_group character varying NOT NULL
);


ALTER TABLE public.group_question OWNER TO postgres;

--
-- Name: question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.question (
                                 id integer NOT NULL,
                                 question character varying NOT NULL,
                                 id_group_question integer NOT NULL
);


ALTER TABLE public.question OWNER TO postgres;

--
-- Name: question_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.question_answer (
                                        id integer NOT NULL,
                                        question_id integer,
                                        id_answer integer NOT NULL
);


ALTER TABLE public.question_answer OWNER TO postgres;

--
-- Name: question_answer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.question_answer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.question_answer_id_seq OWNER TO postgres;

--
-- Name: question_answer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.question_answer_id_seq OWNED BY public.question_answer.id;


--
-- Name: question_carrer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.question_carrer (
                                        id integer NOT NULL,
                                        question_id integer,
                                        career_sigenu_id character varying
);


ALTER TABLE public.question_carrer OWNER TO postgres;

--
-- Name: question_carrer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.question_carrer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.question_carrer_id_seq OWNER TO postgres;

--
-- Name: question_carrer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.question_carrer_id_seq OWNED BY public.question_carrer.id;


--
-- Name: question_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.question_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.question_id_seq OWNER TO postgres;

--
-- Name: question_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.question_id_seq OWNED BY public.question.id;


--
-- Name: questionnaire; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.questionnaire (
                                      id integer NOT NULL,
                                      name character varying NOT NULL,
                                      description character varying
);


ALTER TABLE public.questionnaire OWNER TO postgres;

--
-- Name: questionnaire_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.questionnaire_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.questionnaire_id_seq OWNER TO postgres;

--
-- Name: questionnaire_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.questionnaire_id_seq OWNED BY public.questionnaire.id;


--
-- Name: questionnaire_question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.questionnaire_question (
                                               questionnaire_id integer NOT NULL,
                                               question_id integer NOT NULL
);


ALTER TABLE public.questionnaire_question OWNER TO postgres;

--
-- Name: questionnarie_student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.questionnarie_student (
                                              questionnarie_id integer NOT NULL,
                                              student_sigenu_id character varying NOT NULL,
                                              done_date timestamp without time zone,
                                              id integer NOT NULL
);


ALTER TABLE public.questionnarie_student OWNER TO postgres;

--
-- Name: questionnarie_student_id_questionnaire_student_sigenu_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.questionnarie_student_id_questionnaire_student_sigenu_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.questionnarie_student_id_questionnaire_student_sigenu_seq OWNER TO postgres;

--
-- Name: questionnarie_student_id_questionnaire_student_sigenu_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.questionnarie_student_id_questionnaire_student_sigenu_seq OWNED BY public.questionnarie_student.id;


--
-- Name: student_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student_answer (
                                       student_sigenu_id character varying NOT NULL,
                                       question_answer_id integer NOT NULL,
                                       id integer NOT NULL
);


ALTER TABLE public.student_answer OWNER TO postgres;

--
-- Name: student_answer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.student_answer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.student_answer_id_seq OWNER TO postgres;

--
-- Name: student_answer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.student_answer_id_seq OWNED BY public.student_answer.id;


--
-- Name: type-question_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."type-question_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."type-question_id_seq" OWNER TO postgres;

--
-- Name: type-question_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."type-question_id_seq" OWNED BY public.group_question.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answer ALTER COLUMN id SET DEFAULT nextval('public.answer_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.group_question ALTER COLUMN id SET DEFAULT nextval('public."type-question_id_seq"'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question ALTER COLUMN id SET DEFAULT nextval('public.question_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question_answer ALTER COLUMN id SET DEFAULT nextval('public.question_answer_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question_carrer ALTER COLUMN id SET DEFAULT nextval('public.question_carrer_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionnaire ALTER COLUMN id SET DEFAULT nextval('public.questionnaire_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionnarie_student ALTER COLUMN id SET DEFAULT nextval('public.questionnarie_student_id_questionnaire_student_sigenu_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_answer ALTER COLUMN id SET DEFAULT nextval('public.student_answer_id_seq'::regclass);


--
-- Name: pk_answer_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answer
    ADD CONSTRAINT pk_answer_id PRIMARY KEY (id);


--
-- Name: pk_question_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT pk_question_id PRIMARY KEY (id);


--
-- Name: pk_questionnaire_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionnaire
    ADD CONSTRAINT pk_questionnaire_id PRIMARY KEY (id);


--
-- Name: pk_type-question_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.group_question
    ADD CONSTRAINT "pk_type-question_id" PRIMARY KEY (id);


--
-- Name: question_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question_answer
    ADD CONSTRAINT question_answer_pkey PRIMARY KEY (id);


--
-- Name: question_carrer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question_carrer
    ADD CONSTRAINT question_carrer_pkey PRIMARY KEY (id);


--
-- Name: question_carrer_question_id_career_sigenu_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question_carrer
    ADD CONSTRAINT question_carrer_question_id_career_sigenu_id_key UNIQUE (question_id, career_sigenu_id);


--
-- Name: questionnaire_question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionnaire_question
    ADD CONSTRAINT questionnaire_question_pkey PRIMARY KEY (questionnaire_id, question_id);


--
-- Name: questionnarie_student_id_questionnarie_id_student_sigenu_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionnarie_student
    ADD CONSTRAINT questionnarie_student_id_questionnarie_id_student_sigenu_key UNIQUE (questionnarie_id, student_sigenu_id);


--
-- Name: questionnarie_student_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionnarie_student
    ADD CONSTRAINT questionnarie_student_pkey PRIMARY KEY (id);


--
-- Name: student_answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_answer
    ADD CONSTRAINT student_answer_pkey PRIMARY KEY (id);


--
-- Name: student_answer_student_sigenu_id_question_answer_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_answer
    ADD CONSTRAINT student_answer_student_sigenu_id_question_answer_id_key UNIQUE (student_sigenu_id, question_answer_id);


--
-- Name: fk_question_answer_answer; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question_answer
    ADD CONSTRAINT fk_question_answer_answer FOREIGN KEY (id_answer) REFERENCES public.answer(id);


--
-- Name: fk_question_type-question; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT "fk_question_type-question" FOREIGN KEY (id_group_question) REFERENCES public.group_question(id);


--
-- Name: fk_questionnarie-student_questionnaire; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionnarie_student
    ADD CONSTRAINT "fk_questionnarie-student_questionnaire" FOREIGN KEY (questionnarie_id) REFERENCES public.questionnaire(id);


--
-- Name: question_answer_question_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question_answer
    ADD CONSTRAINT question_answer_question_id_fkey FOREIGN KEY (question_id) REFERENCES public.question(id);


--
-- Name: question_carrer_question_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question_carrer
    ADD CONSTRAINT question_carrer_question_id_fkey FOREIGN KEY (question_id) REFERENCES public.question(id);


--
-- Name: questionnaire_question_question_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionnaire_question
    ADD CONSTRAINT questionnaire_question_question_id_fkey FOREIGN KEY (question_id) REFERENCES public.question(id);


--
-- Name: questionnaire_question_questionnaire_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionnaire_question
    ADD CONSTRAINT questionnaire_question_questionnaire_id_fkey FOREIGN KEY (questionnaire_id) REFERENCES public.questionnaire(id);


--
-- Name: student_answer_question_answer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_answer
    ADD CONSTRAINT student_answer_question_answer_id_fkey FOREIGN KEY (question_answer_id) REFERENCES public.question_answer(id);


--
-- PostgreSQL database dump complete
--

