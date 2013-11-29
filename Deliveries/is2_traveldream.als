module is2_traveldream

sig Stringa {}

sig Data {}

sig Intero {
	numero : Int
}
{
	numero >= 0
}

sig User {
	id : one Intero,
	nome : one Stringa,
	cognome : one Stringa,
	email : one Stringa,
	password : one Stringa,
	pacchettiSalvati : set PacchettoSalvato,
	pacchettiCondivisi : set PacchettoSalvato
}

sig AdvancedUser {
	id : one Intero,
	username : one Stringa,
	password : one Stringa
}

sig Admin {
	username : one Stringa,
	password : one Stringa
}


abstract sig Servizio {
	ID : one Stringa,
	tipologia : one TipoServizio
}

sig Trasporto extends Servizio {
	tipoMezzo : one Stringa,
	societa : one Stringa,
	partenza : one Stringa,
	arrivo : one Stringa
}
{
	tipologia = t_trasporto
}

sig Hotel extends Servizio {
	nome : one Stringa,
	stelle : one Intero,
	descrizione : one Stringa
}
{
	tipologia = t_hotel
	stelle.numero <= 5 && stelle.numero>0
}

sig Escursione extends Servizio {
	nome : one Stringa,
	descrizione : one Stringa
}
{
	tipologia = t_escursione
}

sig Pacchetto {
	ID : one Stringa,
	nome : one Stringa,
	descrizione : one Stringa,
	localita : one Stringa,
	trasporti : some Trasporto,			//almeno un mezzo di trasporto
	hotel : some Hotel,						//almeno un hotel
	escursioni : set Escursione			//anche nessuna escursione 
}

sig PacchettoSalvato {
	ID : one Stringa,
	pacchetto : one Pacchetto,
	dataPartenza : one Data,
	dataRitorno : one Data,
	userCreatore : one User,
	partecipanti : set User
}

enum TipoServizio {t_hotel, t_escursione, t_trasporto }

		pred show() {
			#User = 2
			#PacchettoSalvato = 2
		}

// Amministratore unico
fact AdminUnico {
	#Admin = 1
}

//Non esistono user con stesso id o e-mail
fact userUnici {
	no disj user1, user2: User | user1.id = user2.id || user1.email=user2.email
}

//Non esistono Advanced User con stesso id o username
fact auUnici {
	no disj au1, au2 : AdvancedUser | au1.id = au2.id || au2.username = au2.username
}

//Servizi devono avere ID diversi
fact serviziUnici {
	no disj servizio1, servizio2 : Servizio | servizio1.ID = servizio2.ID
}

//User creatore non puo essere contenuto nella lista degli utenti partecipanti
fact creatoreNoPartecipante {
	no pacchetto : PacchettoSalvato | pacchetto.userCreatore in pacchetto.partecipanti
}
//Tutti i pacchetti che sono stati salvati sono contenuti tra i pacchetti salvati del creatore
fact pacchettoSalvatoSeCreatore {
	all pacchetto : PacchettoSalvato | pacchetto in pacchetto.userCreatore.pacchettiSalvati
} 

//Un utente non può avere un pacchetto sia tra quelli condivisi che tra quelli salvati da ui
fact pacchettoCondivisoDisjSalvato {
	no utente : User | utente.pacchettiSalvati in utente.pacchettiCondivisi || utente.pacchettiCondivisi in utente.pacchettiSalvati
}

//Se esiste almeno un servizio (o un pacchetto) deve esistere almeno un Advanced User
fact esistenzaAU {
	#Servizio > 0 => #AdvancedUser > 0
}

//Solo il creatore del pacchetto puo salvarlo
fact salvaSoloCreatore {
	all utente : User | utente.pacchettiSalvati.userCreatore = utente
}

//Si un utente partecipa ad un pacchetto condiviso sarà presente tra i partecipati di quel pacchetto
fact partecipaPacchettoCondiviso {
	all pacchetto : PacchettoSalvato, utente : User | utente in pacchetto.partecipanti <=> pacchetto in utente.pacchettiCondivisi 
}


pred salvaPacchetto(utente, utente' : User, pacchetto : PacchettoSalvato) {
	(utente'.pacchettiSalvati = utente.pacchettiSalvati + pacchetto) && (pacchetto.userCreatore = utente')
}

pred partecipaPacchetto (utente, utente' : User, pacchetto, pacchetto' : PacchettoSalvato) {
	(pacchetto'.partecipanti = pacchetto.partecipanti + utente') && (utente'.pacchettiCondivisi = utente.pacchettiCondivisi + pacchetto')
}


run show for 3
