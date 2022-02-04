#include <iostream>

using namespace std;

double fun1(double x, double y);
double fun2(double x, double y);
double fun3(double x, double y);

void Euler(int n, double b, double x, double y);
void Heunn(int n, double b, double x, double y);
void Euler2(int n, double b, double x, double y);
int main()
{
    double a=0, b;
    int n, t;
    cout << "Podaj n i b: ";
    cin >> n >> b;
    cout << "1. Euler\n";
    cout << "2. Heun\n";
    cout << "3. Ten trzeci\n";
    cin >> t;
    switch(t)
    {
        case 1:
        {
            double x=0, y=3;
            Euler(n, b, x, y);
            break;
        }
        case 2:
        {
            double x=1, y=2;
            Heunn(n, b, x, y);
            break;
        }
        case 3:
        {
            double x=0, y=1;
            Euler2(n, b, x, y);
            break;
        }
    }

    return 0;
}


double fun1(double x, double y)
{
    return y*y/(x+1);
}

double fun2(double x, double y)
{
    return y/x/x;
}

double fun3(double x, double y)
{
    return 2*x*y;
}

void Euler(int n, double b, double x, double y)
{
    double h=(b-x)/n;
    for(int i=0; i<n; i++)
        {
            y = y + h*fun1(x, y);
            x += h;
        }
    //cout << "x: " << x << endl;
    cout << "y: " << y << endl;
}

void Heunn(int n, double b, double x, double y)
{
    double h=(b-x)/n;
    for(int i=0; i<n; i++)
    {
        double w = fun2(x,y);
        y = y + 0.5*h*(w+fun2(x+h,y+h*w));
        x += h;
    }
    //cout << "x: " << x << endl;
    cout << "y: " << y << endl;
}

void Euler2(int n, double b, double x, double y)
{
    double h=(b-x)/n;
    for(int i=0; i<n; i++)
    {
        y = y + h*fun3(x+h/2,y+h/2*fun3(x,y));
        x += h;
    }
    //cout << "x: " << x << endl;
    cout << "y: " << y << endl;
}
