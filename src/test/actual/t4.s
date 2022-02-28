		.data
	.align 2
Main:	.space 20
		.text
		.globl main
main:
		li $t1, 8
		la $t0, Main
		sw $t1, 12($t0)
		la $t1, Main
		lw $t1, 12($t1)
		addi $t1, $t1, 1
		la $t0, Main
		sw $t1, 12($t0)
		la $t1, Main
		lw $t1, 12($t1)
		la $t0, Main
		sw $t1, 16($t0)
		la $t1, Main
		lw $t1, 12($t1)
		addi $t1, $t1, -1
		la $t0, Main
		sw $t1, 12($t0)
		la $t1, Main
		lw $t1, 12($t1)
		la $t0, Main
		sw $t1, 16($t0)
		la $t1, Main
		lw $t1, 12($t1)
		la $t0, Main
		sw $t1, 16($t0)
		la $t1, Main
		lw $t1, 12($t1)
		addi $t1, $t1, 1
		la $t0, Main
		sw $t1, 12($t0)
		la $t1, Main
		lw $t1, 12($t1)
		la $t0, Main
		sw $t1, 16($t0)
		la $t0, Main
		lw $t0, 12($t0)
		addi $t1, $t1, -1
		la $t0, Main
		sw $t1, 12($t0)
		la $t0, Main
		lw $t0, 12($t0)
		neg $s0, $t0
		move $t1, $s0
		la $t0, Main
		sw $t1, 16($t0)
		li $t1, 3
		la $t0, Main
		lw $t2, 16($t0)
		add $t1, $t1, $t2
		sw $t1, 16($t0)
		li $t1, 4
		la $t0, Main
		lw $t2, 16($t0)
		sub $t1, $t2, $t1
		sw $t1, 16($t0)
		li $t1, 4
		la $t0, Main
		lw $t2, 16($t0)
		mul $t1, $t2, $t1
		sw $t1, 16($t0)
		li $t1, 4
		la $t0, Main
		lw $t2, 16($t0)
		div $t1, $t2, $t1
		sw $t1, 16($t0)
		la $t1, Main
		lw $t1, 12($t1)
		la $t0, Main
		lw $t2, 16($t0)
		add $t1, $t1, $t2
		sw $t1, 16($t0)
		la $t1, Main
		lw $t1, 12($t1)
		la $t0, Main
		lw $t2, 16($t0)
		sub $t1, $t2, $t1
		sw $t1, 16($t0)
		la $t1, Main
		lw $t1, 12($t1)
		la $t0, Main
		lw $t2, 16($t0)
		mul $t1, $t2, $t1
		sw $t1, 16($t0)
		la $t1, Main
		lw $t1, 12($t1)
		la $t0, Main
		lw $t2, 16($t0)
		div $t1, $t2, $t1
		sw $t1, 16($t0)
		la $t0, Main
		lw $t0, 12($t0)
		li $t1, 4
		add $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		lw $t2, 16($t0)
		add $t1, $t1, $t2
		sw $t1, 16($t0)
		la $t0, Main
		lw $t0, 12($t0)
		li $t1, 4
		add $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		lw $t2, 16($t0)
		sub $t1, $t2, $t1
		sw $t1, 16($t0)
		la $t0, Main
		lw $t0, 12($t0)
		li $t1, 4
		add $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		lw $t2, 16($t0)
		mul $t1, $t2, $t1
		sw $t1, 16($t0)
		la $t0, Main
		lw $t0, 12($t0)
		li $t1, 4
		add $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		lw $t2, 16($t0)
		div $t1, $t2, $t1
		sw $t1, 16($t0)
		li $t1, 2
		la $t0, Main
		sw $t1, 8($t0)
		jr $ra
