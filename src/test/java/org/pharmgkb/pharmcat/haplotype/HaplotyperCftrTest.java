package org.pharmgkb.pharmcat.haplotype;

import java.nio.file.Path;
import java.util.List;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.pharmgkb.pharmcat.TestUtil;
import org.pharmgkb.pharmcat.haplotype.model.HaplotyperResult;

import static org.pharmgkb.pharmcat.haplotype.HaplotyperTest.assertDiplotypePairs;
import static org.pharmgkb.pharmcat.haplotype.HaplotyperTest.testCallHaplotype;


/**
 * JUnit test for {@link Haplotyper#callDiplotypes(MatchData)}.
 *
 * @author Lester Carter
 */
public class HaplotyperCftrTest {
  private Path m_definitionFile;

  @Before
  public void before() throws Exception {
    m_definitionFile =  TestUtil.getFile("org/pharmgkb/pharmcat/haplotype/CFTR_translation.json");
  }


  @Test
  public void cftrReferenceReference() throws Exception {
    // Test reference
    Path vcfFile = TestUtil.getFile("org/pharmgkb/pharmcat/haplotype/cftr/refref.vcf");
    List<String> expectedMatches = Lists.newArrayList("Reference/Reference");

    HaplotyperResult result = testCallHaplotype(m_definitionFile, vcfFile);
    assertDiplotypePairs(expectedMatches, result);
  }


  @Test
  public void cftrF508delF508del() throws Exception {
    // Test F508del(TCT)/F508del(TCT)
    Path vcfFile = TestUtil.getFile("org/pharmgkb/pharmcat/haplotype/cftr/F508delF508del.vcf");
    List<String> expectedMatches = Lists.newArrayList("F508del(TCT)/F508del(TCT)");

    HaplotyperResult result = testCallHaplotype(m_definitionFile, vcfFile);
    assertDiplotypePairs(expectedMatches, result);
  }

  @Test
  public void cftrWt5T() throws Exception {
    // Test Reference/5T - TODO - assumption is that repeat is represented correctly
    Path vcfFile = TestUtil.getFile("org/pharmgkb/pharmcat/haplotype/cftr/ref5t.vcf");
    List<String> expectedMatches = Lists.newArrayList("5T/Reference");

    HaplotyperResult result = testCallHaplotype(m_definitionFile, vcfFile);
    assertDiplotypePairs(expectedMatches, result);
  }
}
