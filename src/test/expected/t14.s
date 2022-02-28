		.data
	.align 2
Main:	.space 20
		.text
		.globl main
Main_m:
		la $t0, Main
		lw $t0, 8($t0)
		li $t1, 4
		add $s0, $t0, $t1
		move $t1, $s0
		la $t0, Main
		sw $t1, 4($t0)
		jr $ra
main:
		li $t1, 5
		la $t0, Main
		sw $t1, 8($t0)
		addi $sp, $sp, -4
		sw $ra, 0($sp)
		jal Main_m
		lw $ra, 0($sp)
		addi $sp, $sp, 4
		la $t1, Main
		lw $t1, 4($t1)
		la $t0, Main
		sw $t1, 16($t0)
		li $t1, 2
		la $t0, Main
		sw $t1, 12($t0)
		jr $ra
