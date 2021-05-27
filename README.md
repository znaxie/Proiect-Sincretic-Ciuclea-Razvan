# Proiect-Sincretic-Ciuclea-Razvan

Un grup de N țări trebuie reprezentate pe o hartă cu culori diferite, astfel 
încât oricare dintre acestea să fie colorată diferit de vecinii săi. Să se scrie un 
program care primește la intrare lista celor N țări (denumire, listă vecini), lista 
de culori posibile și determină culoarea pentru fiecare țară în parte.

• In realizarea proiectului s-au folosit grafuri neorientate si metoda greedy.
• Metoda greedy face la nivel local alegerea optima pentru fiecare etapa in 
speranta de a gasi un optim global.
• Altfel spus, pornim de la mulțimea vidă și adăugăm în mod repetat 
în B elemente până când acest lucru nu mai este posibil.
• Stabilirea elementului care va fi adăugat în soluția B se face alegându-l pe 
cel mai bun din acel moment – este un optim local.

1. Se asigneaza culori, incepand din nodul 0, pentru toate nodurile.
2. Se initializeaza nodurile ca fiind neasignate si se asigneaza prima culoare primului nod.
3. Initial, toate culorile sunt disponibile si se asigneaza nodurilor ramase.
4. Se parcurg nodurile adiacente si setam flagul ca fiind indisponibil.
5. Se gaseste prima culoare disponibila, se asigneaza, iar apoi resetam valoarea inapoi la true pentru urmatoarea iteratie(false
inseamna ca available[cr] este asignata unui varf adiacent)
6. Printarea rezultatului
