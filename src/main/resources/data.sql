-- games table-
INSERT INTO games(name,description,team_size,prize_pool,created) VALUES
('TEKKEN','1v1 Elimination Brackets. Final is Bo3',1,100000,CURRENT_DATE()),
('LoL','5v5 Summoners rift tournament draft. Final is Bo5. You can only sign up as a team',5,500000,CURRENT_DATE()),
('Apex','3v3 Elimination bracket. You can only sign up as a team',3,300000,'2023-06-06'),
('Fortnite','25 players Battle royale. Last 5 standing are chosen.',1,100000,'2023-06-06'),
('FIFA','1v1 Elimination brackets. Final is bo3.',1,100000,CURRENT_DATE());

 --teams table
INSERT INTO teams(name,captain_name,captain_email,game_id,created) VALUES
('Smurfies','William smith','william.smith@yahoo.com',(SELECT id FROM games WHERE name='LoL'),CURRENT_TIME()),
('Goulash','Abraham Lincoln','abraham.lincoln@yahoo.com',(SELECT id FROM games WHERE name='Apex'),CURRENT_TIME());


-- players table
INSERT INTO players(name,age,game_tag,email,game_id,created) VALUES
('Marta',24,'knuckleHead','marta.xoxo@yahoo.com',(SELECT id FROM games WHERE name='TEKKEN'),CURRENT_TIME()),
('Barack',16,'smartypantsu','barackobama@yahoo.com',(SELECT id FROM games WHERE name ='Fortnite'),CURRENT_TIME());
