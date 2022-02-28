		.data
	.align 2
Main:	.space 28
		.text
		.globl main
main:
		li.s $f1, 6.0
		la $t0, Main
		s.s $f1, 16($t0)
		la $t0, Main
		lwc1 $f0, 16($t0)
		li.s $f1, 1.0
		sub.s $f0, $f0, $f1
		la $t0, Main
		s.s $f1, 16($t0)
		la $t0, Main
		lwc1 $f1, 16($t0)
		la $t0, Main
		s.s $f1, 20($t0)
		la $t0, Main
		lwc1 $f0, 16($t0)
		li.s $f1, 1.0
		add.s $f0, $f0, $f1
		la $t0, Main
		s.s $f1, 16($t0)
		la $t0, Main
		lwc1 $f1, 16($t0)
		la $t0, Main
		s.s $f1, 20($t0)
		la $t0, Main
		lwc1 $f1, 16($t0)
		la $t0, Main
		s.s $f1, 20($t0)
		la $t0, Main
		l.s $f0, 16($t0)
		li.s $f1, 1.0
		sub.s $f0, $f0, $f1
		la $t0, Main
		s.s $f1, 16($t0)
		la $t0, Main
		lwc1 $f1, 16($t0)
		la $t0, Main
		s.s $f1, 20($t0)
		la $t0, Main
		l.s $f1, 16($t0)
		li.s $f1, 1.0
		add.s $f0, $f0, $f1
		la $t0, Main
		s.s $f1, 16($t0)
		la $t0, Main
		lwc1 $f0, 16($t0)
		neg.s $f3, $f0
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 20($t0)
		li.s $f1, 2.0
		la $t0, Main
		s.s $f1, 20($t0)
		la $t0, Main
		lwc1 $f1, 16($t0)
		la $t0, Main
		lwc1 $f0, 20($t0)
		add.s $f1, $f0, $f1
		s.s $f1, 20($t0)
		la $t0, Main
		lwc1 $f1, 16($t0)
		la $t0, Main
		lwc1 $f0, 20($t0)
		sub.s $f1, $f0, $f1
		s.s $f1, 20($t0)
		la $t0, Main
		lwc1 $f1, 16($t0)
		la $t0, Main
		lwc1 $f0, 20($t0)
		mul.s $f1, $f0, $f1
		s.s $f1, 20($t0)
		la $t0, Main
		lwc1 $f1, 16($t0)
		la $t0, Main
		lwc1 $f0, 20($t0)
		div.s $f1, $f0, $f1
		s.s $f1, 20($t0)
		li $t1, 5
		la $t0, Main
		sw $t1, 24($t0)
		li $t0, 2
		mtc1 $t0, $f3
		cvt.s.w $f3, $f3
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 20($t0)
		la $t0, Main
		lw $t0, 24($t0)
		mtc1 $t0, $f3
		cvt.s.w $f3, $f3
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 20($t0)
		la $t0, Main
		lw $t0, 24($t0)
		li $t1, 5
		add $s0, $t0, $t1
		move $t0, $s0
		mtc1 $t0, $f3
		cvt.s.w $f3, $f3
		mov.s $f1, $f3
		la $t0, Main
		s.s $f1, 20($t0)
		li $t1, 2
		la $t0, Main
		sw $t1, 12($t0)
		jr $ra
