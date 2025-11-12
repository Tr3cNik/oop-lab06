# oop-lab06 Teoria



## Importare Repository da Remote

Possiamo inizializzare una repository vuota con `git init`, ma la maggior parte delle volte vogliamo iniziare da una copia locale di una repository già esistente.



Git dispone del comando `clone` che copia l'intera storia della repository localmente.



Il comando di base è `git clone` URI destination:

- Viene creata la cartella destination e viene clonata la repository trovata nell'URI al suo interno

   - Se destination non è vuota, fallisce

   - Se destination viene omesso, verrà creata una cartella con lo stesso nome dell'ultimo segmento dell'URI

- L'URI può essere preso da cartelle locali, da https e da ssh

- La clone controlla anche il branch remoto a cui è collegato HEAD



Le varie copie che vengono create dalla repository esistente vengono chiamate remotes.
Ognuna di esse ha un nome e un URI.

- Quando il repository è creato con init, non ci sono remote

- Quanto il repository viene importato con clone, si crea localmente la stessa repo con soltanto il branch dove è attaccata la HEAD e viene creata automaticamente una remote chiamata `origin`



## Branch Remote

I branch non-locali si possono riferire con `remoteName/branchName`.



Il comando `remote` si usa per gestire le varie remote:

- `git remote` -v fa una lista delle remote conosciute

- `git remote add a-remote` URI aggiunge una nuova remote (a-remote) che punta all'URI

- `git remote show a-remote` mostra informazioni dettagliate di a-remote

- `git remote remove a-remove` rimuove (dimentica localmente l'esistenza) a-remote


I branch remote possono essere "associati" ai branch locali, nel senso di "entrambi i branch sono copie dello stesso".

Un branch remote associato a un branch locale viene detto il suo `branch upstream`.

- I branch upstream si possono configurare con `git branch --set-upstream-to=remote/branchName`

- Quanto un repository è inizializzato da clone, il suo branch di default viene creato con lo stesso nome che ha nel remote, mentre l'effettivo remote viene impostato come upstream



Tutti i branch possono essere visti con `git branch -a` e lo status corrente di essi si può vedere con `git log --oneline --graph --all`



Una volta che i branch remote sono stati recuperati, si possono importare in locale con `git branch` oppure con `git checkout -b imported-feat origin/branch`.
Facendo questo, viene creato localmente `imported-feat` e viene posto `origin/branch` come upstream.



Solitamente si riusa lo stesso nome di upstream se non ci sono conflitti.
Le versioni più recenti di Git controllano automaticamente le ambiguità (se presenti) dei branch remote.

Ad esempio, usando il comando `git checkout feat/new-client`:

- Viene creato un nuovo branch feat/new-cliente con upstream settato su origin/feat/upstream, ma questo solo se:

   - non ci sono branch locali con lo stesso nome

   - non ci sono ambiguità tra i remote

Solitamente si usa questa pratica per lavori che usano un singolo remote



Il checkout fatto dopo l'aggiunta di più remote sulla stessa repo locale permette di unire in un'unica ramificazione i branch, spostando la HEAD nell'ultimo branch aggiunto.
Facendo così si può operare su remote multipli, ma i nomi dei branch devo essere univoci.

Per averli dello stesso nome si ha bisogno di 2 branch locali con nomi differenti.



## Fetching Updates

Per vedere se un remote può essere aggiornato, Git fornisce il comando `git fetch`:

- `git fetch a-remote` controlla se a-remote ha nuove informazioni. Se la ha, le scarica localmente
 
- `git fetch` senza remote invece:

   - Se la HEAD è attaccata al branch corrente e ha un upstream, aggiorna le info sul remote che hosta il branch upstream

   - Altrimenti, se presente, si aggiorna origin

Per applicare gli aggiornamenti è necessario usare manualmente merge. Questi aggiornamenti consistono in nuovi commit, branch e tag.

Dopo un fetch, lo status della repo remote è aggiornato e può essere osservato come i branch.



## Push e Pull

L'utilizzo simultaneo di fetch e merge è talmente comune che è stato creato un comando unico per queste operazioni: `git pull`.



Git permette anche di mandare i cambiamenti ai remote: `git push remote branch`

• Vengono mandati i cambiamenti del branch corrente al branch remote, e viene aggiornata la HEAD remote

• Se il branch o il remote viene omesso, viene usato il branch upstream

Usare push richiede i diritti di scrittura sulla repo remote e fallisce se il branch da pushare non è discendente del branch di destinazione



Di default, `git push` non manda i tag:

• `git push --tags` manda soltanto i tag

• `git push --follow-tags` manda i commit e dopo i tag



## Hosting e GitHub

GitHub è un cloud per le repository Git. Arrichisce git con dei servizi costruiti sullo strumento:

• `Forks`, cioè le copie di una repository associate tra diversi utenti/organizzazioni

• `Pull request` (o Merge request): richieste formali di pull dalle fork

• Tracciamento degli errori



Le repository sono identificate univocamente da owner/repoName e supporta 2 tipi d'autenticazione:

• HTTPS (Raccomandato su Windows)

• SSH (Raccomandato su UNIX)



# oop-lab06 Esercizi

## Esercizio 61

`~` `git clone https://github.com/APICe-at-DISI/OOP-git-merge-conflict-test.git`

Cloning into 'OOP-git-merge-conflict-test'...

remote: Enumerating objects: 12, done.

remote: Counting objects: 100% (4/4), done.

remote: Compressing objects: 100% (3/3), done.

remote: Total 12 (delta 1), reused 1 (delta 1), pack-reused 8 (from 1)

Receiving objects: 100% (12/12), done.

Resolving deltas: 100% (2/2), done.



`~` `cd OOP-git-merge-conflict-test`

`~/OOP-git-merge-conflict-test │ master` `git status`

On branch master

Your branch is up to date with 'origin/master'.



nothing to commit, working tree clean



`~/OOP-git-merge-conflict-test │ master` `git log --oneline --all --graph --decorate`

\* bed943f (origin/feature) Print author information

| \* 8e0f29c (HEAD -> master, origin/master, origin/HEAD) Change HelloWorld to print the number of available processors

|/

\* d956df6 Create .gitignore

\* 700ee0b Create HelloWorld



`~/OOP-git-merge-conflict-test │ master` `git merge origin/feature`

Auto-merging HelloWorld.java

CONFLICT (content): Merge conflict in HelloWorld.java

Automatic merge failed; fix conflicts and then commit the result.



`~/OOP-git-merge-conflict-test │ master merge ~1` `code .`

`~/OOP-git-merge-conflict-test │ master merge ~1` `git status`

On branch master

Your branch is up to date with 'origin/master'.



You have unmerged paths.

  (fix conflicts and run "git commit")

  (use "git merge --abort" to abort the merge)



Unmerged paths:

  (use "git add <file>..." to mark resolution)

        both modified:   HelloWorld.java



no changes added to commit (use "git add" and/or "git commit -a")

`~/OOP-git-merge-conflict-test │ master merge ~1` `git add HelloWorld.java`

`~/OOP-git-merge-conflict-test │ master merge +1` `git commit`

\[master 84843c7] Merge remote-tracking branch 'origin/feature'



`~/OOP-git-merge-conflict-test │ master ⇡2` `git log --oneline --all --graph --decorate`

\*   84843c7 (HEAD -> master) Merge remote-tracking branch 'origin/feature'

|\\

| \* bed943f (origin/feature) Print author information

\* | 8e0f29c (origin/master, origin/HEAD) Change HelloWorld to print the number of available processors

|/

\* d956df6 Create .gitignore

\* 700ee0b Create HelloWorld



`~/OOP-git-merge-conflict-test │ master ⇡2` `git remote add myRepo https://github.com/Tr3cNik/Ex.-61.git`

`~/OOP-git-merge-conflict-test │ master ⇡2` `git remote -v`

myRepo  https://github.com/Tr3cNik/Ex.-61.git (fetch)

myRepo  https://github.com/Tr3cNik/Ex.-61.git (push)

origin  https://github.com/APICe-at-DISI/OOP-git-merge-conflict-test.git (fetch)

origin  https://github.com/APICe-at-DISI/OOP-git-merge-conflict-test.git (push)



`~/OOP-git-merge-conflict-test │ master ⇡2` `git push myRepo`

Enumerating objects: 15, done.

Counting objects: 100% (15/15), done.

Delta compression using up to 8 threads

Compressing objects: 100% (11/11), done.

Writing objects: 100% (15/15), 1.58 KiB | 1.58 MiB/s, done.

Total 15 (delta 4), reused 10 (delta 2), pack-reused 0 (from 0)

remote: Resolving deltas: 100% (4/4), done.

remote:

remote: Create a pull request for 'master' on GitHub by visiting:

remote:      https://github.com/Tr3cNik/Ex.-61/pull/new/master

remote:

To https://github.com/Tr3cNik/Ex.-61.git

 \* \[new branch]      master -> master



`~/OOP-git-merge-conflict-test │ master ⇡2` `git branch --set-upstream-to=myRepo/master`

branch 'master' set up to track 'myRepo/master'

## Esercizio 62

(Questo per aprire l'esercizio su VSCode)

`~` `cd lab06`

`~/lab06 │ exercises` `cd 62-use-lists-and-maps`

`~/lab06/62-use-lists-and-maps │ exercises` `code .`



1. Compilo con `./gradlew compileJava` ed eseguo con `java -cp "build/classes/java/main" it.unibo.collections.TestPerformance`.
   Ottengo (Converting 1000000 ints to String and inserting them in a Set took 390306874ns (390ms))
2. (Visualizza docs Oracle per vedere come funzionano i metodi e si usa `System.nanoTime()` per tenere traccia del tempo in ns)



## Esercizio 63

(Verifica la correttezza della probabilità)

1. if(failProbability < 0 \&\& failProbability >= 1) {
2.             throw new IllegalArgumentException("failProbability has to be between 0 and 1 excluded");
3.         }



(Aggiungi la cattura di `IOExceptions`)

1. private static void retrySendOnNetworkError(final NetworkComponent server, final String message) {
2.         /\*
3.          \* This method should re-try to send message to the provided server, catching all IOExceptions,
4.          \* until it succeeds.
5.          \*/
6.         boolean res = false;
7.         while(!res) {
8.             try {
9.                 server.sendData(message);
10.                 res = true;
11.             } catch (IOException e) {
12.  
13.             }
14.         }
15.     }
16. 
17.     private static String retryReceiveOnNetworkError(final NetworkComponent server) {
18.         /\*
19.          \* This method should re-try to retrieve information from the provided server, catching all IOExceptions,
20.          \* until it succeeds.
21.          \*/
22.         boolean res = false;
23.         String info = null;
24.         while(!res) {
25.             try {
26.                 info = server.receiveResponse();
27.                 res = true;
28.             } catch (IOException e) {
29.  
30.             }
31.         }
32.         return info;
33.     }

Ciò che è dentro `try` è una possibile soluzione: se non passa viene catturata l'eccezione, altrimenti continua normalmente



(Crea nuova classe classe `NetworkException extends IOException` con 2 costruttori a 0 e 1 argomenti che usano `super`)

1. package it.unibo.exceptions.fakenetwork.api;
2. 
3. import java.io.IOException;
4. 
5. public class NetworkException extends IOException {
6. 
7.     public NetworkException() {
8.         super("Network error: no response");
9.     }
10. 
11.     public NetworkException(String message) {
12.         super("Network error while sending message: " + message);
13.     }
14. 
15. }



(Modifico `accessTheNetwork()` in modo che usi la nuova eccezione)

Basta sostituire in `IOException` la nuova eccezione `NetworkException`



(Modifico `sendData()` in modo che lanci `IllegalArgumentException` piuttosto che stampare)

1. public void sendData(final String data) throws IOException {
2.         accessTheNetwork(data);
3.         final var exceptionWhenParsedAsNumber = nullIfNumberOrException(data);
4.         if (KEYWORDS.contains(data) || exceptionWhenParsedAsNumber == null) {
5.             commandQueue.add(data);
6.         } else {
7.             final var message = data + " is not a valid keyword (allowed: " + KEYWORDS + "), nor is a number";
8.             commandQueue.clear();
9.             throw new IllegalArgumentException(message, exceptionWhenParsedAsNumber);
10.             /\*
11.              \* This method, in this point, should throw an IllegalStateException.
12.              \* Its cause, however, is the previous NumberFormatException.
13.              \* Always preserve the original stacktrace!
14.              \*
15.              \* The previous exceptions must be set as the cause of the new exception
16.              \*/
17.         }
18.     }



(Rimuovo le `println` e metto al loro posto `IllegalStateException`)



(Aggiungo un `try` con tutto il programma già designato, aggiungo una `catch Exception` e scrivo `finally commandQueue.clear()`)



## Esercizio 64

Praticamente l'ho copiato tutto, i commit spiegano ciò che è stato fatto



## Esercizio 65

Creo la classe `GraphImpl` che implementa l'interfaccia `Graph` e verifico l'outcome con il test.
Per implementare il grafo utilizzo una `Map<N, Set<N>>` di `edges`, tutto quello che serve sta nelle mappe.
La ricerca del grafo non la faccio.

