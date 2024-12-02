# Read the file
with open('day1input.txt', 'r') as file:
    lines = file.readlines()

# Initialize two empty arrays
column1 = []
column2 = []

# Iterate through each line and split the columns
for line in lines:
    col1, col2 = line.split()
    column1.append(int(col1))
    column2.append(int(col2))

column1.sort()
column2.sort()

diff = 0
sum = 0
for i in range(len(column1)):
    sum += abs(column1[i] - column2[i])

print("Sum = ",sum) 