
## P1: Check the maximum of 3 numbers

a = int(input("Input the first number: "))
b = int(input("Input the first number: "))
c = int(input("Input the first number: "))
	
if (a >= b) and (a >= c):
   greatest = a
elif (b >= a) and (b >= c):
   greatest = b
else:
   greatest = c
  
print("The largest number is:", greatest)

## ----------------------------------------------

a equals regular read (a)
b equals regular read (b)
c equals regular read (c)

check a greater b y a greater c
	greatest equals a
checkif b greater a y b greater c
	greatest equals b
not
	greatest equals c	

show("The largest number is:", greatest)