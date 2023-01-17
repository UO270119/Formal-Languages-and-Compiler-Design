## P2: Check if number is prime

n = int(input("Input a number: "))
	
if n > 1:
	for i in range (2, n):
		if (n % i) == 0:
			print(n, "is not a prime number")
			break
	else:
		print(n, "is a prime number")
		
		
## ------------------------------------------

n equals regular read (n)

check n greater than 1
	loop var rango (2, n)
		check (n remainder i) equals 0
			show(n, "is not a prime number")
			end
	not:
		show(n, "is a prime number")