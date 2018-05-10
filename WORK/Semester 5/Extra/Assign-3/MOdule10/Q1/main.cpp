#include <iostream>
#include <iomanip>
#include <fstream>
#include <cstdlib> // exit function prototype
#include "ClientData.h" // ClientData class definition
using namespace std;
/*
main.cpp: modified prog38.cpp

This program asks for three options as first record, last record or the record number in addition to asking a user to enter the record no. he wants to see.
@Author: Tanya Teotia
*/


void outputLine( ostream&, const ClientData & ); // prototype
void displayHeader();
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
        cout<<"Enter f to display first record"<<endl;
        cout<<"Enter l to display last record"<<endl;
        cout<<"Enter n for reading a record number"<<endl;
        cout<<"Enter r for a random record"<<endl;
        cout<<"Enter e to exit"<<endl;
        cin>>ch;
 displayHeader();
        //conditional switch statement.
        switch(ch){
        case 'f':
            inCredit.seekg (sizeof(ClientData)*(1-1));
            inCredit.read( reinterpret_cast< char * >( &client ),
      sizeof( ClientData ) );
            outputLine( cout, client );
            break;

        case 'l':
             inCredit.seekg (sizeof(ClientData)*(3-1));
            inCredit.read( reinterpret_cast< char * >( &client ),
      sizeof( ClientData ) );
            outputLine( cout, client );
            break;

        case 'n':
            cout << "Enter record to read ";
            cin >> r;
            if( r <= 0 || r >= 3
 ){
                cout << "record " << r << " doesn't exist";
            }else{
                 inCredit.seekg (sizeof(ClientData)*(r-1));
            inCredit.read( reinterpret_cast< char * >( &client ),
      sizeof( ClientData ) );
                outputLine( cout, client);
            }
            break;
        case 'r':
            r = rand() % 2;
           inCredit.seekg (sizeof(ClientData)*(r-1), ios::cur);
            inCredit.read( reinterpret_cast< char * >( &client ),
      sizeof( ClientData ) );
                outputLine( cout, client);
            break;
        }

        if (ch !='f' && ch!='l' && ch!='r' && ch != 'n' && ch!='e')
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
