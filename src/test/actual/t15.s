		.data
	.align 2
Main2:	.space 16
	.align 2
Main:	.space 44
		.text
		.globl main
Main2_main:
		li $t1, 6
		la $t0, Main2
		sw $t1, 12($t0)
		li $t1, 2
		la $t0, Main2
		sw $t1, 8($t0)
		jr $ra
main:
		li $t1, 9
		la $t0, Main
		sw $t1, 28($t0)
		la $t1, Main
		lw $t1, 28($t1)
		la $t0, Main
		sw $t1, 40($t0)
		li $t1, 2
		la $t0, Main
		sw $t1, 20($t0)
		jr $ra
