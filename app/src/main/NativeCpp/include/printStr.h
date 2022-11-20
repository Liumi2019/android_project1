#ifndef APP_PRINTSTR_H
#define APP_PRINTSTR_H

#include <iostream>
#include <string>

class Str
{

public:
    Str() : str("hello"){}
    ~Str() {}

    std::string printStrP();

private:
    std::string str;
};




#endif //APP_PRINTSTR_H
