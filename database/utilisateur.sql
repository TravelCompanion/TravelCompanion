-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Dim 28 Mai 2017 à 12:04
-- Version du serveur :  5.7.17-log
-- Version de PHP :  7.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `test`
--

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_user` int(11) UNSIGNED NOT NULL,
  `userName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_user`, `userName`, `email`, `password`) VALUES
(1, 'Fay Knox', 'ipsum.dolor@dictum.co.uk', 'id, mollis nec, cursus a, enim. Suspendisse aliquet, sem'),
(2, 'Cora Carson', 'eu@Sedeget.co.uk', 'eu, ligula. Aenean euismod mauris'),
(3, 'Chantale Hart', 'Nullam.scelerisque@Morbi.net', 'a purus. Duis elementum, dui quis accumsan convallis, ante lectus convallis est, vitae sodales nisi magna sed dui.'),
(4, 'Vincent Poole', 'sodales@id.org', 'Etiam laoreet, libero et tristique pellentesque, tellus sem mollis dui, in sodales elit erat vitae'),
(5, 'Mufutau Holloway', 'non.cursus.non@nonloremvitae.net', 'at augue id ante dictum cursus. Nunc mauris elit, dictum eu, eleifend nec,'),
(6, 'Kimberly Freeman', 'sed.facilisis.vitae@felispurusac.org', 'et magnis dis parturient montes, nascetur'),
(7, 'Xanthus Harmon', 'lobortis.mauris@risusquis.edu', 'Aliquam rutrum lorem ac risus. Morbi metus. Vivamus euismod urna. Nullam lobortis quam a'),
(8, 'Illiana Mendez', 'lorem@congueelitsed.com', 'cursus vestibulum. Mauris magna. Duis dignissim tempor arcu. Vestibulum ut eros'),
(9, 'Halee Ferrell', 'interdum@Integer.co.uk', 'amet ornare lectus justo eu arcu. Morbi sit amet massa. Quisque porttitor eros nec tellus. Nunc'),
(10, 'Malcolm Parker', 'lorem@purus.net', 'sed orci lobortis augue scelerisque mollis. Phasellus libero mauris, aliquam eu,'),
(11, 'Wynne Turner', 'nisi@dis.co.uk', 'dolor elit, pellentesque a, facilisis non, bibendum sed, est. Nunc laoreet lectus quis massa. Mauris vestibulum, neque'),
(12, 'Kai Clay', 'massa.Mauris.vestibulum@montes.edu', 'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac'),
(13, 'Danielle Gardner', 'Nullam@etrutrumnon.org', 'torquent per conubia nostra, per inceptos hymenaeos. Mauris ut quam vel sapien imperdiet ornare. In'),
(14, 'Jenna Odonnell', 'nonummy.ac@ullamcorpermagnaSed.co.uk', 'sed, facilisis vitae, orci. Phasellus dapibus quam quis diam. Pellentesque habitant morbi'),
(15, 'Gage Harrell', 'leo.Morbi.neque@dignissimmagnaa.co.uk', 'augue ac ipsum. Phasellus vitae mauris sit amet lorem'),
(16, 'Alyssa Christensen', 'eu.nibh.vulputate@metus.com', 'urna. Nunc quis arcu vel quam dignissim'),
(17, 'September Ware', 'in.dolor.Fusce@elitCurabitur.com', 'urna suscipit nonummy. Fusce fermentum fermentum arcu. Vestibulum'),
(18, 'Rina Jimenez', 'enim@orciluctus.co.uk', 'ipsum. Suspendisse non leo. Vivamus nibh dolor, nonummy ac,'),
(19, 'Doris Pratt', 'Curabitur@mauris.com', 'dictum. Proin eget odio. Aliquam vulputate ullamcorper magna. Sed eu eros. Nam'),
(20, 'Prescott Lynn', 'Integer.sem.elit@lacusQuisque.net', 'vitae mauris sit amet lorem semper auctor. Mauris vel'),
(21, 'Yuri Holcomb', 'dolor.Donec.fringilla@leoMorbi.co.uk', 'montes, nascetur ridiculus mus. Donec dignissim magna a tortor. Nunc commodo'),
(22, 'Aladdin Hebert', 'facilisis.facilisis@nequevitae.co.uk', 'nibh. Donec est mauris, rhoncus'),
(23, 'Quyn Rich', 'velit.eu.sem@egestashendreritneque.org', 'laoreet, libero et tristique pellentesque,'),
(24, 'Byron Sears', 'malesuada@atsem.org', 'vulputate dui, nec tempus mauris erat'),
(25, 'Christian Morris', 'urna.Nunc@massaMaurisvestibulum.net', 'orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu vel quam dignissim pharetra. Nam ac'),
(26, 'Nasim Frazier', 'aliquet@non.ca', 'sed, facilisis vitae, orci. Phasellus dapibus quam quis diam. Pellentesque habitant morbi'),
(27, 'Dorothy Pennington', 'consectetuer@temporloremeget.co.uk', 'a purus. Duis elementum, dui quis accumsan convallis, ante lectus convallis est, vitae sodales nisi magna sed'),
(28, 'Sean Kemp', 'et.netus.et@quistristiqueac.ca', 'gravida nunc sed pede. Cum sociis natoque penatibus et magnis dis parturient montes,'),
(29, 'Nasim Horton', 'nec.diam@Etiamlaoreet.co.uk', 'Donec tempor, est ac mattis semper, dui'),
(30, 'Kirestin Bright', 'rutrum.urna.nec@atiaculisquis.net', 'nibh. Quisque nonummy ipsum non'),
(31, 'Eaton Garner', 'Curabitur.sed@leo.co.uk', 'ornare, facilisis eget, ipsum. Donec sollicitudin adipiscing ligula. Aenean'),
(32, 'Hall Russo', 'sed@nibhPhasellusnulla.ca', 'dui. Cras pellentesque. Sed dictum. Proin eget odio.'),
(33, 'Oscar Snow', 'ut.nisi@Vivamus.net', 'laoreet lectus quis massa. Mauris vestibulum, neque sed dictum eleifend, nunc'),
(34, 'Brielle Sweet', 'amet@Sedmalesuada.ca', 'sem, vitae aliquam eros turpis non enim.'),
(35, 'MacKensie Mccullough', 'et@fringillacursuspurus.ca', 'molestie tortor nibh sit amet orci. Ut sagittis lobortis mauris. Suspendisse aliquet molestie tellus. Aenean egestas'),
(36, 'Sandra Berry', 'enim@ametfaucibus.edu', 'Etiam imperdiet dictum magna. Ut tincidunt orci quis lectus. Nullam suscipit, est ac facilisis facilisis, magna tellus faucibus'),
(37, 'Adrian Reese', 'ut.mi.Duis@anequeNullam.ca', 'habitant morbi tristique senectus et netus et malesuada'),
(38, 'Breanna Huff', 'mi@quam.ca', 'pede, malesuada vel, venenatis vel, faucibus id, libero.'),
(39, 'Larissa Bryan', 'Aliquam@Nunc.net', 'a, scelerisque sed, sapien. Nunc pulvinar arcu et'),
(40, 'Carter Moreno', 'risus.odio@justo.ca', 'adipiscing fringilla, porttitor vulputate, posuere'),
(41, 'Judah Puckett', 'quam.Curabitur@Sednec.edu', 'eu odio tristique pharetra. Quisque ac'),
(42, 'Emi Brady', 'pretium@Nullasemper.net', 'suscipit, est ac facilisis facilisis, magna'),
(43, 'Constance Richard', 'nisi.dictum.augue@Donecelementum.co.uk', 'nec luctus felis purus ac tellus. Suspendisse sed dolor. Fusce mi lorem, vehicula et, rutrum'),
(44, 'Aspen Dale', 'quam.elementum@elita.com', 'at, libero. Morbi accumsan laoreet ipsum. Curabitur consequat, lectus sit amet'),
(45, 'Pascale Hodges', 'sapien@Phasellus.org', 'Proin vel arcu eu odio tristique pharetra.'),
(46, 'Alexa Burns', 'Cras@mollisDuis.net', 'nascetur ridiculus mus. Aenean eget magna.'),
(47, 'Tallulah Becker', 'dapibus.gravida.Aliquam@orcilobortis.com', 'orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu vel quam'),
(48, 'Zeus Frank', 'mollis.non@ornareegestasligula.ca', 'sem. Pellentesque ut ipsum ac mi eleifend egestas. Sed pharetra, felis eget varius ultrices, mauris ipsum'),
(49, 'Ruby Blankenship', 'massa@ante.com', 'dictum eleifend, nunc risus varius orci, in consequat enim diam vel arcu.'),
(50, 'Baxter Mckinney', 'nascetur@Suspendissecommodotincidunt.co.uk', 'lacus, varius et, euismod et, commodo at, libero. Morbi accumsan laoreet ipsum. Curabitur'),
(51, 'Tamara Reynolds', 'eleifend@Donec.org', 'senectus et netus et malesuada fames ac turpis'),
(52, 'Alexa Irwin', 'neque.pellentesque.massa@molestie.edu', 'Sed id risus quis diam luctus lobortis. Class aptent taciti sociosqu ad litora'),
(53, 'Uriel Mooney', 'consectetuer.rhoncus@dignissim.com', 'dictum magna. Ut tincidunt orci quis lectus. Nullam suscipit, est ac facilisis facilisis, magna tellus faucibus leo, in'),
(54, 'Sloane Solis', 'sodales.elit.erat@iaculisnec.net', 'feugiat. Sed nec metus facilisis lorem tristique aliquet. Phasellus fermentum convallis ligula. Donec luctus'),
(55, 'Stewart Lyons', 'Donec@Nullaeget.ca', 'justo. Proin non massa non ante bibendum ullamcorper. Duis cursus, diam at pretium aliquet,'),
(56, 'Arthur Camacho', 'ac.ipsum@enimgravidasit.co.uk', 'dapibus gravida. Aliquam tincidunt, nunc'),
(57, 'Summer Flynn', 'tempus.non@luctus.net', 'mauris sit amet lorem semper auctor. Mauris vel turpis. Aliquam adipiscing lobortis risus. In'),
(58, 'Maya Doyle', 'accumsan.interdum.libero@Integeraliquam.edu', 'et pede. Nunc sed orci lobortis augue scelerisque mollis. Phasellus libero mauris, aliquam eu, accumsan'),
(59, 'Joan Alvarado', 'magna@massarutrum.net', 'vestibulum massa rutrum magna. Cras convallis convallis dolor. Quisque'),
(60, 'Samuel Sears', 'luctus@Nullamlobortisquam.ca', 'at, egestas a, scelerisque sed, sapien. Nunc pulvinar arcu'),
(61, 'Colette Mcneil', 'fermentum.metus@cursusnonegestas.ca', 'Aliquam erat volutpat. Nulla facilisis. Suspendisse commodo tincidunt nibh. Phasellus nulla. Integer'),
(62, 'Gannon Noel', 'feugiat.nec@odiovelest.co.uk', 'sem elit, pharetra ut, pharetra sed, hendrerit a, arcu.'),
(63, 'Madison Chambers', 'mi.lacinia.mattis@Nulla.ca', 'est ac facilisis facilisis, magna'),
(64, 'Daryl Doyle', 'Morbi.metus.Vivamus@nequevenenatis.com', 'nunc risus varius orci, in consequat enim'),
(65, 'Branden Pollard', 'lorem.semper@eleifend.ca', 'nulla. Integer urna. Vivamus molestie'),
(66, 'Pandora Bass', 'mattis.Integer.eu@velitegetlaoreet.ca', 'at, iaculis quis, pede. Praesent eu dui. Cum sociis natoque penatibus et magnis dis parturient'),
(67, 'Thane Riddle', 'Sed@dui.edu', 'lacinia. Sed congue, elit sed'),
(68, 'Xanthus Winters', 'ullamcorper@tinciduntnibhPhasellus.com', 'eu, ultrices sit amet, risus. Donec nibh enim, gravida sit amet, dapibus id,'),
(69, 'Brett Vance', 'diam@aaliquet.org', 'dictum augue malesuada malesuada. Integer id magna et ipsum cursus vestibulum. Mauris magna. Duis dignissim tempor'),
(70, 'Ira Copeland', 'lorem@ad.ca', 'arcu vel quam dignissim pharetra. Nam'),
(71, 'Ryan Howe', 'Sed.nulla@pellentesque.co.uk', 'tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam fringilla cursus purus. Nullam scelerisque'),
(72, 'Jaden White', 'sapien@CraspellentesqueSed.org', 'lobortis. Class aptent taciti sociosqu'),
(73, 'Cameron Pacheco', 'vel@Suspendissecommodotincidunt.edu', 'consequat purus. Maecenas libero est, congue a, aliquet vel, vulputate eu, odio. Phasellus at augue id'),
(74, 'Glenna Avila', 'diam.lorem@cubiliaCurae.net', 'at fringilla purus mauris a nunc. In at pede.'),
(75, 'Raphael Ferrell', 'ipsum@auctornuncnulla.ca', 'id ante dictum cursus. Nunc mauris elit, dictum eu, eleifend nec, malesuada'),
(76, 'Jarrod Saunders', 'ornare.placerat@loremluctusut.com', 'odio, auctor vitae, aliquet nec, imperdiet'),
(77, 'Mercedes Davidson', 'magna.Suspendisse.tristique@arcu.org', 'libero. Morbi accumsan laoreet ipsum. Curabitur consequat, lectus sit amet luctus'),
(78, 'Xantha Gregory', 'vel.faucibus.id@vulputateduinec.net', 'et, rutrum eu, ultrices sit amet, risus. Donec nibh enim, gravida sit amet,'),
(79, 'Cora Alvarado', 'Maecenas@tinciduntcongueturpis.org', 'sed pede nec ante blandit viverra. Donec tempus, lorem fringilla ornare placerat,'),
(80, 'September Foster', 'purus@lectusantedictum.ca', 'imperdiet, erat nonummy ultricies ornare, elit elit fermentum risus, at fringilla purus mauris'),
(81, 'Maxine Copeland', 'sit.amet@non.com', 'Nam nulla magna, malesuada vel, convallis in, cursus'),
(82, 'Teegan Ramirez', 'nibh.Phasellus@non.org', 'non, sollicitudin a, malesuada id, erat. Etiam vestibulum massa'),
(83, 'Connor Harrell', 'pede.Suspendisse.dui@vitaemaurissit.ca', 'at, egestas a, scelerisque sed, sapien. Nunc'),
(84, 'Yardley Pickett', 'neque.tellus@neceuismodin.com', 'eu metus. In lorem. Donec elementum, lorem ut aliquam'),
(85, 'Lillian Brady', 'sed.orci.lobortis@ametloremsemper.net', 'Nulla tempor augue ac ipsum. Phasellus'),
(86, 'Luke Alford', 'malesuada@nibh.ca', 'elit. Aliquam auctor, velit eget laoreet'),
(87, 'Charde Spence', 'dolor.sit@Phaselluslibero.edu', 'arcu vel quam dignissim pharetra. Nam ac nulla. In'),
(88, 'Clinton Sherman', 'egestas.Duis.ac@nonummy.net', 'penatibus et magnis dis parturient montes, nascetur ridiculus'),
(89, 'Quintessa Holcomb', 'consectetuer.rhoncus.Nullam@augue.ca', 'quam a felis ullamcorper viverra. Maecenas iaculis aliquet diam. Sed diam lorem, auctor quis, tristique ac, eleifend vitae,'),
(90, 'Upton Summers', 'fringilla@rhoncus.co.uk', 'morbi tristique senectus et netus et malesuada fames ac'),
(91, 'Buffy Singleton', 'Quisque.nonummy.ipsum@risus.com', 'porttitor interdum. Sed auctor odio a purus. Duis elementum, dui quis accumsan convallis, ante lectus'),
(92, 'Ronan Tyler', 'neque.pellentesque.massa@Donecest.com', 'In lorem. Donec elementum, lorem ut aliquam iaculis, lacus'),
(93, 'Olympia Hartman', 'augue@Phasellusornare.ca', 'amet, dapibus id, blandit at, nisi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus'),
(94, 'Wynne Cantrell', 'Ut@gravidamauris.com', 'urna. Nunc quis arcu vel quam dignissim pharetra. Nam ac'),
(95, 'Otto Richmond', 'non.justo@loremDonec.org', 'sed libero. Proin sed turpis nec mauris blandit mattis.'),
(96, 'Rhiannon Aguirre', 'accumsan.neque.et@dolor.edu', 'orci. Phasellus dapibus quam quis diam. Pellentesque habitant morbi tristique senectus et'),
(97, 'Nyssa Buck', 'luctus.Curabitur.egestas@egetipsumSuspendisse.net', 'elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu imperdiet ullamcorper. Duis at lacus.'),
(98, 'Ariel Garcia', 'auctor@eratvolutpatNulla.com', 'convallis convallis dolor. Quisque tincidunt pede ac urna. Ut tincidunt vehicula risus. Nulla eget metus eu erat semper'),
(99, 'Elton Paul', 'Vestibulum.ut.eros@nisl.org', 'Integer aliquam adipiscing lacus. Ut nec urna et arcu imperdiet ullamcorper. Duis at lacus. Quisque purus sapien, gravida'),
(100, 'Mollie Schroeder', 'metus.vitae.velit@mollisduiin.ca', 'feugiat nec, diam. Duis mi enim, condimentum eget, volutpat ornare, facilisis eget, ipsum. Donec');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_user` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
