		.data
	.align 2
Main:	.space 8
	.align 2
Main2:	.space 12
		.text
		.globl main
main:
		li.s $f1, 8.0
		la $t0, Main
		s.s $f1, 4($t0)
		jr $ra
Main2_main:
		li $t1, 4
		la $t0, Main
		sw $t1, 0($t0)
		la $t1, Main
		lw $t1, 0($t1)
		la $t0, Main2
		sw $t1, 12($t0)
		li.s $f1, 8.0
		la $t0, Main2
		s.s $f1, 4($t0)
		jr $ra
