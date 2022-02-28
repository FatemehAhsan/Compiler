		.data
	.align 2
Main2:	.space 16
	.align 2
Main:	.space 28
		.text
		.globl main
Main2_m:
		li $t1, 4
		la $t0, Main2
		sw $t1, 4($t0)
		jr $ra
Main2_main:
		addi $sp, $sp, -4
		sw $ra, 0($sp)
		jal Main2_m
		lw $ra, 0($sp)
		addi $sp, $sp, 4
		la $t1, Main2
		lw $t1, 4($t1)
		la $t0, Main2
		sw $t1, 12($t0)
		li $t1, 2
		la $t0, Main2
		sw $t1, 8($t0)
		jr $ra
Main_m2:
		li $t1, 4
		la $t0, Main
		sw $t1, 16($t0)
		jr $ra
main:
		addi $sp, $sp, -4
		sw $ra, 0($sp)
		jal Main_m2
		lw $ra, 0($sp)
		addi $sp, $sp, 4
		la $t1, Main
		lw $t1, 16($t1)
		la $t0, Main
		sw $t1, 24($t0)
		addi $sp, $sp, -4
		sw $ra, 0($sp)
		jal Main2_m
		lw $ra, 0($sp)
		addi $sp, $sp, 4
		la $t1, Main2
		lw $t1, 4($t1)
		la $t0, Main
		sw $t1, 24($t0)
		la $t1, Main
		lw $t1, 24($t1)
		la $t0, Main2
		sw $t1, 12($t0)
		addi $sp, $sp, -4
		sw $ra, 0($sp)
		jal Main2_main
		lw $ra, 0($sp)
		addi $sp, $sp, 4
		la $t1, Main2
		lw $t1, 8($t1)
		la $t0, Main
		sw $t1, 24($t0)
		li $t1, 2
		la $t0, Main
		sw $t1, 20($t0)
		jr $ra
