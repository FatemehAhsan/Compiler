		.data
	.align 2
Main:	.space 28
		.text
		.globl main
main:
		li $t1, 5
		la $t0, Main
		sw $t1, 16($t0)
		li $t1, 7
		la $t0, Main
		sw $t1, 20($t0)
		li $t0, 3
		li $t1, 6
		sgt $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		li $t0, 3
		li $t1, 6
		slt $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		li $t0, 3
		li $t1, 6
		seq $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		li $t0, 3
		li $t1, 6
		sne $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		li $t0, 3
		li $t1, 6
		sle $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		li $t0, 3
		li $t1, 6
		sge $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		la $t1, Main
		lw $t1, 20($t1)
		sgt $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		la $t1, Main
		lw $t1, 20($t1)
		slt $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		la $t1, Main
		lw $t1, 20($t1)
		seq $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		la $t1, Main
		lw $t1, 20($t1)
		sne $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		la $t1, Main
		lw $t1, 20($t1)
		sle $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		la $t1, Main
		lw $t1, 20($t1)
		sge $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		li $t1, 4
		add $s0, $t0, $t1
		move $t0, $s0
		la $t1, Main
		lw $t1, 20($t1)
		sgt $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		li $t1, 5
		add $s0, $t0, $t1
		move $t0, $s0
		la $t1, Main
		lw $t1, 20($t1)
		slt $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		li $t1, 5
		add $s0, $t0, $t1
		move $t0, $s0
		la $t1, Main
		lw $t1, 20($t1)
		seq $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		li $t1, 5
		add $s0, $t0, $t1
		move $t0, $s0
		la $t1, Main
		lw $t1, 20($t1)
		sne $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		li $t1, 5
		add $s0, $t0, $t1
		move $t0, $s0
		la $t1, Main
		lw $t1, 20($t1)
		sle $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		li $t1, 6
		add $s0, $t0, $t1
		move $t0, $s0
		la $t1, Main
		lw $t1, 20($t1)
		sge $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		li $t0, 4
		sgt $s0, $t0, $0
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		sgt $s0, $t0, $0
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 16($t0)
		li $t1, 6
		add $s0, $t0, $t1
		move $t0, $s0
		sgt $s0, $t0, $0
		move $t1, $s0
		la $t0, Main
		sw $t1, 24($t0)
		li $t1, 2
		la $t0, Main
		sw $t1, 12($t0)
		jr $ra
