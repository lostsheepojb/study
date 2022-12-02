#1 for语句练习1
sum=0
for i in {1..10}
do
	sum=$[i+sum]
done
echo "1到10的和：$sum"

#2 for语句练习2
sum=0
for i in {1..10..2}
do
	sum=$[sum+i]
done
echo "1到10的奇数和：$sum"

#3 for语句练习3

sum=0
for((i=1; i <= 10;i++ ))
do
	sum=$[sum+i]
done

echo "1到10的和：$sum"
 
#4 if语句练习
grade=1243

if [ $grade -lt 0 ]
	then echo "得分不正常"
	exit
fi

if [ $grade -lt 60 ]
	then echo "评级为D级"
elif [ $grade -lt 80 ]
	then echo "评级为C级"
elif [ $grade -lt 90 ]
	then echo "评级为B级"
elif [ $grade -le 100 ]
	then echo "评级为A级"
else 
	echo "评级为S级"
fi

#5 if语句练习2
grade=-199
if (( grade < 0 ))
	then echo "得分不正常"
	else echo "nice"
fi

#6 while语句练习

i=0
sum=0
while(( i < 10 ))
do
	sum=$[sum+i]
	i=$[i+1]
done

echo "1到10的和：$sum"