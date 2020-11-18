USE projeto_recomendacao;

DELIMITER $$
CREATE TRIGGER name
    AFTER DELETE
    ON table FOR EACH ROW
BEGIN
    
END$$    
DELIMITER ;