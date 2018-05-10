
#include "SalariedEmployee.h"

#include <iostream>

using std::cout;



// SalariedEmployee constructor
SalariedEmployee::SalariedEmployee( const string &first,
                                    const string &last, const string &socialSecurityNumber, const Date &bDay,
                                    double salary )
    : Employee( first, last, socialSecurityNumber, bDay )
{
    setWeeklySalary( salary );

} // end SalariedEmployee constructor

// set salaried worker's salary
void SalariedEmployee::setWeeklySalary( double salary )
{
    weeklySalary = salary < 0.0 ? 0.0 : salary;

} // end function setWeeklySalary

// calculate salaried worker's pay
double SalariedEmployee::earnings() const
{
    return getWeeklySalary();

} // end function earnings

// return salaried worker's salary
double SalariedEmployee::getWeeklySalary() const
{
    return weeklySalary;

} // end function getWeeklySalary

// print salaried worker's name
void SalariedEmployee::print() const
{
    cout << "\nsalaried employee: ";
    Employee::print();  // code reuse

} // end function print
