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

sim_score = 0
index = 0
count = 0

for i in column1:
    if i in column2:
        index = column2.index(i)
        while column2[index]==i:
            count+=1
            index+=1
    else:
        count=0
    sim_score += i*count

print("Similarity score = ",sim_score)