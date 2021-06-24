-- TODO:
-- 코드를 입력하세요
SELECT a.NAME, a.DATETIME
FROM ANIMAL_INS a
LEFT JOIN ANIMAL_OUTS b
ON a.ANIMAL_ID = b.ANIMAL_ID
ORDER BY a.DATETIME
LIMIT 3;

SELECT b.bno, b.title, b.writer_email, r.rno, r.text
FROM Board b
LEFT OUTER JOIN Reply r
ON b.bno = r.board_bno
WHERE b.bno = 100;
