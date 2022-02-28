		.data
	.align 2
Main:	.space 36
		.text
		.globl main
main:
		li $t1, 1
		la $t0, Main
		sw $t1, 20($t0)
		li $t1, 0
		la $t0, Main
		sw $t1, 24($t0)
		li $t0, 1
		li $t1, 1
		and $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 28($t0)
		la $t0, Main
		lw $t0, 20($t0)
		la $t1, Main
		lw $t1, 24($t1)
		and $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 28($t0)
		li $t0, 1
		li $t1, 1
		or $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 28($t0)
		la $t0, Main
		lw $t0, 20($t0)
		la $t1, Main
		lw $t1, 24($t1)
		or $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 28($t0)
		li $t0, 1
		la $t1, Main
		lw $t1, 24($t1)
		and $s0, $t0, $t1
		la $t0, Main
		lw $t0, 20($t0)
		move $t1, $s0
		and $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 20($t0)
		la $t1, Main
		lw $t1, 24($t1)
		and $s0, $t0, $t1
		la $t0, Main
		lw $t0, 20($t0)
		move $t1, $s0
		and $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		li $t1, 1
		la $t0, Main
		sw $t1, 20($t0)
		li $t1, 0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 20($t0)
		seq $s0, $t0, $0
		move $t1, $s0
		la $t0, Main
		sw $t1, 28($t0)
		li $t1, 5
		la $t0, Main
		sw $t1, 32($t0)
		li $t0, 4
		sgt $s0, $t0, $0
		move $t1, $s0
		la $t0, Main
		sw $t1, 28($t0)
		la $t0, Main
		lw $t0, 32($t0)
		sgt $s0, $t0, $0
		move $t1, $s0
		la $t0, Main
		sw $t1, 28($t0)
		la $t0, Main
		lw $t0, 32($t0)
		li $t1, 6
		add $s0, $t0, $t1
		move $t0, $s0
		sgt $s0, $t0, $0
		move $t1, $s0
		la $t0, Main
		sw $t1, 28($t0)
		li $t1, 2
		la $t0, Main
		sw $t1, 16($t0)
		jr $ra
