COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.answer (
    id integer NOT NULL,
    answer character varying NOT NULL
);


--
-- Name: answer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.answer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


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


--
-- Name: question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.question (
    id integer NOT NULL,
    question character varying NOT NULL,
    description character varying,
    id_group_question integer NOT NULL
);


--
-- Name: question_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.question_answer (
    id_question integer NOT NULL,
    id_asnwer integer NOT NULL
);


--
-- Name: question_carrer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.question_carrer (
    id_question integer NOT NULL,
    id_carrer_sigenu character varying NOT NULL
);


--
-- Name: question_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.question_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


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


--
-- Name: questionnaire_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.questionnaire_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: questionnaire_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.questionnaire_id_seq OWNED BY public.questionnaire.id;


--
-- Name: questionnaire_question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.questionnaire_question (
    id_questionnaire integer NOT NULL,
    id_question integer NOT NULL
);


--
-- Name: questionnarie_student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.questionnarie_student (
    id_questionnarie integer NOT NULL,
    id_student_sigenu character varying NOT NULL,
    done_date timestamp without time zone
);


--
-- Name: student_answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student_answer (
    id_student_sigenu character varying NOT NULL,
    id_answer integer NOT NULL,
    id_question integer NOT NULL
);


--
-- Name: type-question_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."type-question_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


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

ALTER TABLE ONLY public.questionnaire ALTER COLUMN id SET DEFAULT nextval('public.questionnaire_id_seq'::regclass);


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
-- Name: fk_question-answer_answer; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question_answer
    ADD CONSTRAINT "fk_question-answer_answer" FOREIGN KEY (id_asnwer) REFERENCES public.answer(id);


--
-- Name: fk_question-answer_question; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question_answer
    ADD CONSTRAINT "fk_question-answer_question" FOREIGN KEY (id_question) REFERENCES public.question(id);


--
-- Name: fk_question_carrer_question; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question_carrer
    ADD CONSTRAINT fk_question_carrer_question FOREIGN KEY (id_question) REFERENCES public.question(id);


--
-- Name: fk_question_type-question; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT "fk_question_type-question" FOREIGN KEY (id_group_question) REFERENCES public.group_question(id);


--
-- Name: fk_questionnaire-question_question; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionnaire_question
    ADD CONSTRAINT "fk_questionnaire-question_question" FOREIGN KEY (id_question) REFERENCES public.question(id);


--
-- Name: fk_questionnaire-question_questionnaire; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionnaire_question
    ADD CONSTRAINT "fk_questionnaire-question_questionnaire" FOREIGN KEY (id_questionnaire) REFERENCES public.questionnaire(id);


--
-- Name: fk_questionnarie-student_questionnaire; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.questionnarie_student
    ADD CONSTRAINT "fk_questionnarie-student_questionnaire" FOREIGN KEY (id_questionnarie) REFERENCES public.questionnaire(id);


--
-- Name: fk_student-answer_answer; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_answer
    ADD CONSTRAINT "fk_student-answer_answer" FOREIGN KEY (id_answer) REFERENCES public.answer(id);


--
-- Name: fk_student-answer_question; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_answer
    ADD CONSTRAINT "fk_student-answer_question" FOREIGN KEY (id_question) REFERENCES public.question(id);