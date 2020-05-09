INSERT INTO role VALUES
    (0, 'ROLE_USER'),
    (1, 'ROLE_ADMIN')
;

INSERT INTO account VALUES
    (0, 'admin', '$2y$12$bRzgIgSedb05g0SOSGh5FOicEtoiXLPCUaOMnrT24ZjT3RWYME2bC', true)
;

INSERT INTO account_role VALUES
    (0, 0, 1)
;