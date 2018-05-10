#include <iostream>
#include <iomanip>
#include <fstream>
#include <cstdlib> // exit function prototype
#include "ClientData.h" // ClientData class definition
using namespace std;
/*
main.cpp: modified prog38.cpp

This program asks for three options as to create a random file, read it sequentially and read it randomly.
@Author: Tanya Teotia
*/


void outputLine( ostream&, const ClientData & ); // prototype
void displayHeader();
void createNew();
int main()
{
   ifstream inCredit( "clients.dat", ios::in | ios::binary );

   // exit program if ifstream cannot open file
   if ( !inCredit )
   {
      cerr << "File could not be opened." << endl;
      exit( EXIT_FAILURE );
   } // end if

   ClientData client; // create record
int r;

//TODO : Ask  three options as first record, last record or the record number
// Read the record based on the user value

      char ch;

    while (ch!='e')
    {
        //Input the choice.
        cout <<endl<<endl;
         cout<<"Enter c to create a new random file"<<endl;
        cout<<"Enter r for a random record"<<endl;
        cout<<"Enter s for a sequential read through"<<endl;
        cout<<"Enter e to exit"<<endl;
        cin>>ch;
        //conditional switch statement.
        switch(ch){
         case 'c':
            createNew();
            cout << "File has been created with 3 new members!" << endl;
            break;
        case 's':
            displayHeader();
            inCredit.seekg (ios::beg);
            inCredit.read( reinterpret_cast< char * >( &client ),sizeof( ClientData ) );
            while ( inCredit && !inCredit.eof() )
    {
    outputLine( cout, client );
    inCredit.read( reinterpret_cast< char * >( &client ),sizeof( ClientData ) );

    }
            break;

        case 'r':
            displayHeader();
            r = rand() % 2;
           inCredit.seekg (sizeof(ClientData)*(r-1), ios::cur);
            inCredit.read( reinterpret_cast< char * >( &client ),
      sizeof( ClientData ) );
                outputLine( cout, client);
            break;

             }


        if (ch!='r' &&  ch!='c' && ch != 's')
        {
            cout<<"Syntax Error";
            continue;
        }


        if (ch=='e')
        break;
    }

}

void displayHeader(){
    cout << left << setw( 10 ) << "Account" << setw( 16 )
    << "Last Name" << setw( 11 ) << "First Name" << left
    << setw( 10 ) << right << "Balance" << endl;
}

// display single record
void outputLine( ostream &output, const ClientData &record )
{
   output << left << setw( 10 ) << record.getAccountNumber()
      << setw( 16 ) << record.getLastName()
      << setw( 11 ) << record.getFirstName()
      << setw( 10 ) << setprecision( 2 ) << right << fixed
      << showpoint << record.getBalance() << endl;
} // end function outputLine


void createNew(){
    ofstream outCredit( "clients.dat", ios::out | ios::binary );

   // exit program if ofstream could not open file
   if ( !outCredit )
   {
      cerr << "File could not be opened." << endl;
      exit( EXIT_FAILURE );
   } // end if
   int account;
   char lname[ 15 ];
   char fname[ 10 ];
   double balance;
   ClientData blankClient[3];
   for ( int i = 0; i < 3; ++i )
   {
       cout << "enter first name : ";
       cin >> fname;
       cout << "enter last name : ";
       cin >> lname;
       cout << "enter account number : ";
       cin >> account;
       cout << "enter balance : ";
       cin >> balance;
       blankClient[i].setFirstName(fname);
       blankClient[i].setLastName(lname);
       blankClient[i].setAccountNumber(account);
       blankClient[i].setBalance(balance);

   }
    // constructor zeros out each data member

   // output 100 blank records to file
   for ( int i = 0; i < 3; ++i )
      outCredit.write( reinterpret_cast< const char * >( &blankClient[i] ),
         sizeof( ClientData ) );
}

