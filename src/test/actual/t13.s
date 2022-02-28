		.data
	.align 2
Main:	.space 36
		.text
		.globl main
main:
		li $v0, 9
		li $t0, 4
		li $t1, 5
		mul $t1, $t0, $t1
		move $a0, $t1
		syscall
		move $t1, $v0
		la $t0, Main
		sw $t1, 20($t0)
		li $v0, 9
		li $t0, 4
		li $t1, 3
		mul $t1, $t0, $t1
		move $a0, $t1
		syscall
		move $t1, $v0
		la $t0, Main
		sw $t1, 24($t0)
		la $t0, Main
		lw $t0, 20($t0)
		li $t1, 0
		li $t2, 4
		mul $t1, $t1, $t2
		add $s0, $t0, $t1
		li $t1, 4
		sw $t1, 0($s0)
		la $t0, Main
		lw $t0, 20($t0)
		li $t1, 1
		li $t2, 4
		mul $t1, $t1, $t2
		add $s0, $t0, $t1
		li $t1, 2
		sw $t1, 0($s0)
		la $t0, Main
		lw $t0, 20($t0)
		li $t1, 2
		li $t2, 4
		mul $t1, $t1, $t2
		add $s0, $t0, $t1
		la $t0, Main
		lw $t0, 20($t0)
		li $t1, 1
		li $t2, 4
		mul $t1, $t1, $t2
		add $s1, $t0, $t1
		move $t1, $s1
		lw $t1, 0($t1)
		sw $t1, 0($s0)
		la $t0, Main
		lw $t0, 20($t0)
		li $t1, 2
		li $t2, 4
		mul $t1, $t1, $t2
		add $s0, $t0, $t1
		la $t0, Main
		lw $t0, 20($t0)
		li $t1, 1
		li $t2, 4
		mul $t1, $t1, $t2
		add $s1, $t0, $t1
		la $t0, Main
		lw $t0, 20($t0)
		li $t1, 0
		li $t2, 4
		mul $t1, $t1, $t2
		add $s2, $t0, $t1
		move $t0, $s1
		lw $t0, 0($t0)
		move $t1, $s2
		lw $t1, 0($t1)
		add $s1, $t0, $t1
		move $t1, $s1
		sw $t1, 0($s0)
		la $t0, Main
		addi $t0, $t0, 20
		lw $t0, 0($t0)
		la $t1, Main
		addi $t1, $t1, 24
		lw $t1, 0($t1)
		seq $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 28($t0)
		li $t0, 3
		move $s0, $t0
		move $t1, $s0
		la $t0, Main
		sw $t1, 32($t0)
		li $v0, 9
		li $t0, 4
		li $t1, 9
		mul $t1, $t0, $t1
		move $a0, $t1
		syscall
		move $t0, $v0
		li $t0, 9
		move $s0, $t0
		move $t1, $s0
		la $t0, Main
		sw $t1, 32($t0)
		li $t1, 2
		la $t0, Main
		sw $t1, 16($t0)
		jr $ra
