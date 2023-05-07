	.data
_a:	.word 0
	.data
_b:	.word 0
	.data
_t:	.word 0
	.data
_f:	.word 0
	.text
	.globlmain
main:		# METHOD ENTRY
	sw    $ra, 0($sp)	# PUSH
	subu  $sp, $sp, 4
	sw    $fp, 0($sp)	# PUSH
	subu  $sp, $sp, 4
