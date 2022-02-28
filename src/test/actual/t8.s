		.data
	.align 2
Main:	.space 28
		.text
		.globl main
main:
		li.s $f1, 2.0
		la $t0, Main
		s.s $f1, 16($t0)
		li.s $f1, 6.0
		la $t0, Main
		s.s $f1, 20($t0)
		li.s $f0, 3.4
		li.s $f1, 6.1
		add.s $f3, $f0, $f1
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 24($t0)
		li.s $f0, 3.4
		li.s $f1, 6.1
		sub.s $f3, $f0, $f1
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 24($t0)
		li.s $f0, 3.4
		li.s $f1, 6.1
		mul.s $f3, $f0, $f1
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 24($t0)
		li.s $f0, 3.4
		li.s $f1, 6.1
		div.s $f3, $f0, $f1
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 24($t0)
		li.s $f0, 3.4
		li.s $f1, 6.1
		div.s $f2, $f0, $f1
		trunc.w.s $f2, $f2  
		cvt.s.w $f2, $f2 
		mul.s $f1, $f2, $f1
		sub.s $f3, $f0, $f1
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 24($t0)
		la $t0, Main
		lwc1 $f0, 20($t0)
		la $t0, Main
		lwc1 $f1, 16($t0)
		add.s $f3, $f0, $f1
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 24($t0)
		la $t0, Main
		lwc1 $f0, 20($t0)
		la $t0, Main
		lwc1 $f1, 16($t0)
		sub.s $f3, $f0, $f1
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 24($t0)
		la $t0, Main
		lwc1 $f0, 20($t0)
		la $t0, Main
		lwc1 $f1, 16($t0)
		mul.s $f3, $f0, $f1
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 24($t0)
		la $t0, Main
		lwc1 $f0, 20($t0)
		la $t0, Main
		lwc1 $f1, 16($t0)
		div.s $f3, $f0, $f1
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 24($t0)
		la $t0, Main
		lwc1 $f0, 20($t0)
		la $t0, Main
		lwc1 $f1, 16($t0)
		div.s $f2, $f0, $f1
		trunc.w.s $f2, $f2  
		cvt.s.w $f2, $f2 
		mul.s $f1, $f2, $f1
		sub.s $f3, $f0, $f1
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 24($t0)
		la $t0, Main
		lwc1 $f0, 20($t0)
		li.s $f1, 4.0
		add.s $f3, $f0, $f1
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 24($t0)
		la $t0, Main
		lwc1 $f0, 20($t0)
		li.s $f1, 4.0
		sub.s $f3, $f0, $f1
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 24($t0)
		la $t0, Main
		lwc1 $f0, 20($t0)
		li.s $f1, 4.0
		mul.s $f3, $f0, $f1
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 24($t0)
		la $t0, Main
		lwc1 $f0, 20($t0)
		li.s $f1, 4.0
		div.s $f3, $f0, $f1
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 24($t0)
		la $t0, Main
		lwc1 $f0, 20($t0)
		li.s $f1, 4.0
		div.s $f2, $f0, $f1
		trunc.w.s $f2, $f2  
		cvt.s.w $f2, $f2 
		mul.s $f1, $f2, $f1
		sub.s $f3, $f0, $f1
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 24($t0)
		li $t1, 2
		la $t0, Main
		sw $t1, 12($t0)
		jr $ra
