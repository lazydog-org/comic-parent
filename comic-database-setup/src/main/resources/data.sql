use comic;

insert into image_type
    (value, directory_path)
values 
    ('Incoming', '/image/incoming'),
    ('Comic', '/image/comic'),
    ('Title', '/image/title'),
    ('Publisher', '/image/publisher'),
    ('Character', '/image/character'),
    ('Certificate', '/image/certificate');

insert into title_type
    (value)
values 
    ('Ongoing'),
    ('Mini-Series'),
    ('One-Shot'),
    ('Magazine'),
    ('Graphic Novel');

insert into publisher
    (name)
values 
    ('Acclaim'),
    ('Charlton'),    
    ('Comico'),      
    ('CrossGen'),
    ('Dark Horse'),
    ('DC'),
    ('Defiant'),
    ('Eclipse'),
    ('First'),
    ('Gold Key'),
    ('Harvey'),
    ('Image'),
    ('Innovation'),
    ('Malibu'),
    ('Marvel'),
    ('Now'),
    ('Realm Press'),
    ('Topps'),
    ('Valiant'),
    ('Whitman');

insert into comic_type
    (value)
values 
    ('Regular'),
    ('Annual'),
    ('Special'),
    ('Giant-Sized'),
    ('Hardcover'),
    ('Paperback'),
    ('Trade Paperback');

insert into distribution
    (value)
values 
    ('Direct'),
    ('Newsstand'),
    ('Mail Away'),
    ('Store Giveaway'),
    ('Convention');

insert into profession
    (value)
values 
    ('Colorist'),
    ('Editor'),
    ('Inker'),
    ('Penciller'),
    ('Writer');

insert into trait
    (value)
values 
    ('Alternate Cover'),
    ('Autographed'),
    ('Black and White'),
    ('Chrome Cover'),
    ('Die Cut'),
    ('Dynamic Forces'),
    ('Flipbook'),
    ('Foil Cover'),
    ('Free Comic Book Day'),
    ('Gatefold Cover'),
    ('Glow in the Dark'),
    ('Gold Ink Cover'),
    ('Hardcover'),
    ('Hero Special'),
    ('Holo-Graphics Foil Cover'),
    ('Hologram'),
    ('Painted Cover'),
    ('Photo Cover'),
    ('Platinum Edition'),
    ('Polybagged'),
    ('Prism Foil Cover'),
    ('Red Ink Cover'),
    ('Sideways'),
    ('Silver Ink Cover'),
    ('Softcover'),
    ('Wizard'),
    ('Wizard Ace Edition'),
    ('Wizard World Edition');

insert into comic_grade 
    (code, scale, name)
values 
    ('GM',   10.0, 'Gem Mint'),
    ('MT',    9.9, 'Mint'),
    ('NM/MT', 9.8, 'Near Mint/Mint'),
    ('NM+',   9.6, 'Near Mint+'),
    ('NM',    9.4, 'Near Mint'),
    ('NM-',   9.2, 'Near Mint-'),
    ('VF/NM', 9.0, 'Very Fine/Near Mint'),
    ('VF+',   8.5, 'Very Fine+'),
    ('VF',    8.0, 'Very Fine'),
    ('VF-',   7.5, 'Very Fine-'),
    ('FN/VF', 7.0, 'Fine/Very Fine'),
    ('FN+',   6.5, 'Fine+'),
    ('FN',    6.0, 'Fine'),
    ('FN-',   5.5, 'Fine-'),
    ('VG/FN', 5.0, 'Very Good/Fine'),
    ('VG+',   4.5, 'Very Good+'),
    ('VG',    4.0, 'Very Good'),
    ('VG-',   3.5, 'Very Good-'),
    ('GD/VG', 3.0, 'Good/Very Good'),
    ('GD+',   2.5, 'Good+'),
    ('GD',    2.0, 'Good'),
    ('GD-',   1.8, 'Good-'),
    ('FR/GD', 1.5, 'Fair/Good'),
    ('FR',    1.0, 'Fair');