#include <iostream>
#include <conio.h>
#include <string>
using std::cout;
using std::endl;
using std::to_string;
using std::string;
using namespace std;

class Date
{
private:
   int month;
   int date;
   int year;
public:

    Date(){    // default constructor to initialize feet and inches
        month=0;
        date=0;
        year=0;
    }

 Date(int month, int date, int year)       //parameterized constructor
    {
        this->month = month;
         this->date = date;
         this->year = year;
    }


    int getMonth() const{ // method used to input the brithday month details
        return month; }

    string display() const{
        return to_string(month) + "/"
         + to_string(date) + "/"
         + to_string(year);
    }
};
