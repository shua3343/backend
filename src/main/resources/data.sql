insert into CATEGORY(code, description)
values
    (1, 'Comportamental'),
    (2, 'Programação'),
    (3, 'Qualidade'),
    (4, 'Processos')
ON CONFLICT DO NOTHING;

commit;