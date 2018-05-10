
#include <iostream>
#include <iomanip>
#include <vector>
#include <typeinfo>
#include <ctime>
#include "Employee.h"
#include "HourlyEmployee.h"
#include "SalariedEmployee.h"
#include "CommissionEmployee.h"
#include "BasePlusCommissionEmployee.h"
using namespace std;
/*
driver.cpp

This program throws an exception if its employee's birthday
@Author: Tanya Teotia
*/


class Message : public exception{ //This class handles birthday exception
    public :
    const char* message(){
    return "Happy Birthday.";
    }};

int main()
{
     int month;

    time_t timenow = time(0);   // getting current time in no. of seconds
    struct tm * t = localtime( &timenow ); // getting the current time using structure type of tm


    month= t->tm_mon+1; // current month

    try{
    Employee *Employees[4];  //array of Employee variable
    Employees[ 0 ] = new SalariedEmployee("John", "Smith", "111-11-1111", Date(3,11,1991), 800 );
    Employees[ 1 ] = new CommissionEmployee("Sue", "Jones","333-33-3333", Date(6,12,1994), 10000, .06 );
    Employees[ 2 ] = new HourlyEmployee("John", "Charles", "111-11-1111", Date(5,10,1985), 26, 80 );
    Employees[ 3 ] = new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444",Date(3,18,1985), 5000, .04, 300 );

        for ( Employee *employeePtr : Employees ) // polymorphically process each element in array of employees
    {
        employeePtr->print(); // output all employee information
        cout << endl;

         cout << "earned $: " << employeePtr->earnings() << endl;// printing the earning of all employees

        if(month == employeePtr->getMonth()) //checking if its employee's brithday month and adding $100 in earnings
        {
                Message m;
                throw m;
            }

            cout << "\n";
        }
    }
  catch(Message m){
        cout << m.message() <<endl; //calling message method of Message class to print "Happy birthday"
        cout << "An exception occurred because its a birthday month.";
    }
}
