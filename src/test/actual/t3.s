		.data
	.align 2
Main:	.space 28
		.text
		.globl main
main:
		li $t1, 8
		la $t0, Main
		sw $t1, 16($t0)
		li $t1, 6
		la $t0, Main
		sw $t1, 20($t0)
		li $t0, 6
		li $t1, 8
		add $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		li $t0, 7
		li $t1, 8
		sub $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		li $t0, 5
		li $t1, 5
		mul $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		li $t0, 5
		li $t1, 5
		div $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		li $t0, 9
		li $t1, 4
		rem $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		li $t1, 4
		add $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		li $t1, 4
		sub $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		li $t1, 4
		mul $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		li $t1, 4
		div $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		li $t1, 4
		rem $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		la $t1, Main
		lw $t1, 20($t1)
		add $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		la $t1, Main
		lw $t1, 20($t1)
		add $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		la $t1, Main
		lw $t1, 20($t1)
		sub $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		la $t1, Main
		lw $t1, 20($t1)
		mul $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		la $t1, Main
		lw $t1, 20($t1)
		div $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		la $t1, Main
		lw $t1, 20($t1)
		rem $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		li $t1, 2
		la $t0, Main
		sw $t1, 12($t0)
		jr $ra
