		.data
	.align 2
Main:	.space 44
		.text
		.globl main
main:
		li $t1, 0
		la $t0, Main
		sw $t1, 24($t0)
		li $t1, 1
		la $t0, Main
		sw $t1, 28($t0)
		la $t0, Main
		lw $t0, 24($t0)
		la $t1, Main
		lw $t1, 28($t1)
		xor $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 32($t0)
		la $t0, Main
		lw $t0, 24($t0)
		la $t1, Main
		lw $t1, 28($t1)
		and $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 32($t0)
		la $t0, Main
		lw $t0, 24($t0)
		la $t1, Main
		lw $t1, 28($t1)
		or $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 32($t0)
		li $t1, 0
		la $t0, Main
		sw $t1, 36($t0)
		li.s $f1, 5.0
		la $t0, Main
		s.s $f1, 40($t0)
		li.s $f0, 4.0
		cvt.w.s $f0, $f0
		mfc1 $s0, $f0
		move $t1, $s0
		la $t0, Main
		sw $t1, 32($t0)
		li $t0, 1
		move $s0, $t0
		move $t1, $s0
		la $t0, Main
		sw $t1, 32($t0)
		la $t0, Main
		lw $t0, 36($t0)
		move $s0, $t0
		move $t1, $s0
		la $t0, Main
		sw $t1, 32($t0)
		la $t0, Main
		lwc1 $f0, 40($t0)
		cvt.w.s $f0, $f0
		mfc1 $s0, $f0
		move $t1, $s0
		la $t0, Main
		sw $t1, 32($t0)
		la $t0, Main
		lw $t0, 36($t0)
		li $t1, 1
		or $s0, $t0, $t1
		move $t0, $s0
		move $s0, $t0
		move $t1, $s0
		la $t0, Main
		sw $t1, 32($t0)
		la $t0, Main
		lwc1 $f0, 40($t0)
		li.s $f1, 5.7
		add.s $f3, $f0, $f1
		mov.s $f0, $f3
		cvt.w.s $f0, $f0
		mfc1 $s0, $f0
		move $t1, $s0
		la $t0, Main
		sw $t1, 32($t0)
		li $t1, 2
		la $t0, Main
		sw $t1, 20($t0)
		jr $ra
