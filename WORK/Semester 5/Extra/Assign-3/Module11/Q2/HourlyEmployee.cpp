
#include <iostream>

using std::cout;
using std::endl;

#include "HourlyEmployee.h"  // Employee class definition

HourlyEmployee::HourlyEmployee( const string &first,
                                const string &last, const string &socialSecurityNumber, const Date &bDay,
                                double hourlyWage, double hoursWorked )
    : Employee( first, last, socialSecurityNumber, bDay )
{
    setWage( hourlyWage );
    setHours( hoursWorked );

} // end HourlyEmployee constructor

// set hourly worker's wage
void HourlyEmployee::setWage( double wageAmount )
{
    wage = wageAmount < 0.0 ? 0.0 : wageAmount;

} // end function setWage

// set hourly worker's hours worked
void HourlyEmployee::setHours( double hoursWorked )
{
    hours = ( hoursWorked >= 0.0 && hoursWorked <= 168.0 ) ?
            hoursWorked : 0.0;

} // end function setHours

// return hours worked
double HourlyEmployee::getHours() const
{
    return hours;

} // end function getHours

// return wage
double HourlyEmployee::getWage() const
{
    return wage;

} // end function getWage

// get hourly worker's pay
double HourlyEmployee::earnings() const
{
    if ( hours <= 40 )  // no overtime
        return wage * hours;
    else                // overtime is paid at wage * 1.5
        return 40 * wage + ( hours - 40 ) * wage * 1.5;

} // end function earnings

// print hourly worker's information
void HourlyEmployee::print() const
{
    cout << "\nhourly employee: ";
    Employee::print();  // code reuse

} // end function print
